package com.ford.mediadata.mgt.service;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wanglijun on 16/5/7.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public abstract class AbstractSpringTest  extends AbstractTransactionalJUnit4SpringContextTests {

    protected  final Logger log= LoggerFactory.getLogger(super.getClass());


}
