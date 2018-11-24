package com.ford.mediadata.mgt.web;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.ford.mediadata.mgt.ApplicationContextTest;
import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SqlImport extends ApplicationContextTest {

	@Resource
	private DataSource dataSource;

	private ExecutorService service = Executors.newFixedThreadPool(5);

	@Test
	public void test() {
		executeSqlFile("/datadrive/Feb_Order.sql");
		executeSqlFile("/datadrive/Feb_Call.sql");
	}

	public void executeSqlFile(String filePath) {
		try {
			List<Future<Object>> futures = Lists.newArrayList();
			File file = new File(filePath);
			@Cleanup
			FileInputStream input = new FileInputStream(file);
			List<String> lines = IOUtils.readLines(input);
			List<List<String>> sqlsList = splitList(lines, 10000);
			for (List<String> sqls : sqlsList) {
				SqlThread thread = new SqlThread(dataSource, sqls);
				Future<Object> future = service.submit(thread);
				futures.add(future);
				log.info("创建线程，共有SQL{}条", sqls.size());
			}
			for (Future<Object> future : futures) {
				future.get();
			}
			log.info("线程全部结束，主线程退出");
		} catch (Exception e) {
			log.error("主线程异常", e);
		}
	}

	@Getter
	@AllArgsConstructor
	private class SqlThread implements Callable<Object> {

		private DataSource dataSource;

		private List<String> sqls;

		@Override
		public Object call() throws Exception {
			try {
				log.info("线程开始，执行SQL{}条", sqls.size());
				@Cleanup
				Connection connection = dataSource.getConnection();
				connection.setAutoCommit(true);
				String notEndSql = "";
				for (String sql : sqls) {
					try {
						if (!sql.endsWith(";")) {
							notEndSql = notEndSql + sql;
							continue;
						} else if (StringUtils.isNotEmpty(notEndSql)) {
							sql = notEndSql + sql;
							notEndSql = "";
						}
						sql = StringUtils.removeEnd(sql, ";");
						@Cleanup
						Statement statement = connection.createStatement();
						statement.executeUpdate(sql);
					} catch (Exception e) {
						log.error("SQL执行异常：{}，当前语句：{}", e.getMessage(), sql);
					}
				}
				log.info("线程结束，执行SQL{}条", sqls.size());
			} catch (Exception e) {
				log.error("线程异常：{}", e.getMessage());
			}
			return new Object();
		}

	}

	private <T> List<List<T>> splitList(List<T> sourceList, Integer maxCountPerList) {
		List<List<T>> resultList = Lists.newArrayList();
		if (CollectionUtils.isEmpty(sourceList)) {
			return resultList;
		}
		List<T> tempList = Lists.newArrayList();
		for (T t : sourceList) {
			if (tempList.size() >= maxCountPerList) {
				resultList.add(tempList);
				tempList = Lists.newArrayList();
			}
			tempList.add(t);
		}
		if (CollectionUtils.isNotEmpty(tempList)) {
			resultList.add(tempList);
		}
		return resultList;
	}

}
