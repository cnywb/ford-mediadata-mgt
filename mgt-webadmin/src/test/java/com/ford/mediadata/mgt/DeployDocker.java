package com.ford.mediadata.mgt;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.ContainerPort;
import io.fabric8.kubernetes.api.model.DoneableReplicationController;
import io.fabric8.kubernetes.api.model.DoneableService;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.PodSpec;
import io.fabric8.kubernetes.api.model.PodTemplateSpec;
import io.fabric8.kubernetes.api.model.Quantity;
import io.fabric8.kubernetes.api.model.ResourceRequirements;
import io.fabric8.kubernetes.api.model.ServicePort;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeployDocker {

	final private static String namespace = "ford";
	final private static String instance = "mgt";
	final private static Integer replicas = 1;
	final private static String cpu = "1";
	final private static String memory = "2048";
	final private static String INSTANCE_ID = "instanceId";

	/** newtouch test */
	final private static String host = "mgt.one1.newtouch.com";
	final private static String k8sApiUrl = "http://k8s.one1.newtouch.com";
	final private static String image = "172.28.1.236:5000/newtouchone/mgt";

	public static void main(String args[]) {
		try {
			@Cleanup
			KubernetesClient kubernetesClient = new DefaultKubernetesClient(k8sApiUrl);
			deleteReplicationControllerIfExist(kubernetesClient);
			createReplicationController(kubernetesClient);
			createServiceIfNotExist(kubernetesClient);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	private static void deleteReplicationControllerIfExist(KubernetesClient kubernetesClient) {
		if (CollectionUtils.isNotEmpty(
				kubernetesClient.replicationControllers().withLabel(INSTANCE_ID, instance).list().getItems())) {
			kubernetesClient.replicationControllers().withLabel(INSTANCE_ID, instance).delete();
			log.info("exist replication controllers deleted");
		}
		if (CollectionUtils.isNotEmpty(kubernetesClient.pods().withLabel(INSTANCE_ID, instance).list().getItems())) {
			kubernetesClient.pods().withLabel(INSTANCE_ID, instance).delete();
			log.info("exist pods deleted");
		}
	}

	private static void createReplicationController(KubernetesClient kubernetesClient) {
		/** label */
		Map<String, String> labels = Maps.newHashMap();
		labels.put(INSTANCE_ID, instance);
		/** selector */
		Map<String, String> selector = Maps.newHashMap();
		selector.put(INSTANCE_ID, instance);
		/** pod template */
		PodTemplateSpec pod = new PodTemplateSpec();
		pod.setMetadata(new ObjectMeta());
		pod.getMetadata().setName(instance);
		pod.getMetadata().setNamespace(namespace);
		pod.getMetadata().setLabels(labels);
		pod.setSpec(new PodSpec());
		{
			List<Container> containers = Lists.newArrayList();
			{
				Container container = new Container();
				container.setName(instance);
				container.setImage(image);
				container.setImagePullPolicy("Always");
				{
					List<ContainerPort> ports = Lists.newArrayList();
					{
						ContainerPort containerPort = new ContainerPort();
						containerPort.setContainerPort(8080);
						ports.add(containerPort);
					}
					container.setPorts(ports);
				}
				{
					ResourceRequirements resources = new ResourceRequirements();
					{
						Map<String, Quantity> limits = Maps.newHashMap();
						limits.put("cpu", new Quantity(cpu));
						limits.put("memory", new Quantity(memory + "M"));
						resources.setLimits(limits);
					}
					container.setResources(resources);
				}
				containers.add(container);
			}
			pod.getSpec().setContainers(containers);
		}
		{
			Map<String, String> nodeSelector = Maps.newHashMap();
			nodeSelector.put("FUN", "DOCKER");
			pod.getSpec().setNodeSelector(nodeSelector);
		}
		pod.getSpec().setRestartPolicy("Always");
		/** rc */
		DoneableReplicationController rc = kubernetesClient.replicationControllers().createNew();
		rc = rc.withNewMetadata().withName(instance).withNamespace(namespace).endMetadata();
		rc = rc.withNewSpec().withReplicas(replicas).withSelector(selector).withNewTemplateLike(pod).endTemplate()
				.endSpec();
		/** done */
		rc.done();
		log.info("new replication controllers created");
	}

	private static void createServiceIfNotExist(KubernetesClient kubernetesClient) {
		if (CollectionUtils
				.isNotEmpty(kubernetesClient.services().withLabel(INSTANCE_ID, instance).list().getItems())) {
			log.info("service already exist, skip create service");
			return;
		}
		/** label */
		Map<String, String> labels = Maps.newHashMap();
		labels.put(INSTANCE_ID, instance);
		/** selector */
		Map<String, String> selector = Maps.newHashMap();
		selector.put(INSTANCE_ID, instance);
		/** ports */
		List<ServicePort> ports = Lists.newArrayList();
		{
			ServicePort port = new ServicePort();
			port.setPort(8080);
			ports.add(port);
		}
		/** service */
		DoneableService service = kubernetesClient.services().createNew();
		service.withNewMetadata().withName(instance).withNamespace(namespace).withLabels(labels).endMetadata();
		service.withNewSpec().withType("NodePort").withPorts(ports).withSelector(selector).endSpec();
		/** done */
		service.done();
		log.info("new service created");
		DoneableService doneableService = kubernetesClient.services().inNamespace(namespace).withName(instance).edit();
		Integer nodePort = doneableService.getSpec().getPorts().get(0).getNodePort();
		doneableService.editMetadata()
				.addToAnnotations("kubernetesReverseproxy", "{\"hosts\": [{\"host\": \"" + host
						+ "\", \"http\": \"true\", \"port\": \"80\",\"nodePort\":\"" + nodePort + "\"}]}")
				.endMetadata();
		doneableService.done();
		log.info("service annotation updated");
	}

}