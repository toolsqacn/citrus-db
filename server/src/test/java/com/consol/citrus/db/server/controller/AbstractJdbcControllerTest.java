/*
 * Copyright 2006-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.consol.citrus.db.server.controller;

import com.consol.citrus.db.driver.dataset.DataSet;
import com.consol.citrus.db.server.JdbcServerException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class AbstractJdbcControllerTest {

    //Class under test
    private AbstractJdbcController jdbcController;

    //return values for stubbed spy methods
    private final int affectedRows = new Random().nextInt();
    private final DataSet dataSet = mock(DataSet.class);

    //GIVEN
    private String sql = "statement";

    @BeforeMethod
    private void setUp(){
        jdbcController = spy(new AbstractJdbcController() {
            @Override
            protected DataSet handleQuery(final String sql) throws JdbcServerException {
                return null;
            }

            @Override
            protected DataSet handleExecute(final String sql) throws JdbcServerException {
                return null;
            }

            @Override
            protected int handleUpdate(final String sql) throws JdbcServerException {
                return 0;
            }
        });

        doReturn(dataSet).when(jdbcController).handleQuery(anyString());
        doReturn(dataSet).when(jdbcController).handleExecute(anyString());
        doReturn(affectedRows).when(jdbcController).handleUpdate(anyString());
    }

    @Test
    public void testExecuteQueryDelegatesToHandleQuery(){
        //WHEN
        DataSet dataSet = jdbcController.executeQuery(sql);

        //THEN
        verify(jdbcController).handleQuery(sql);
        Assert.assertEquals(dataSet, this.dataSet);
    }

    @Test
    public void testExecuteDelegatesToHandleExecute(){
        //WHEN
        DataSet dataSet = jdbcController.executeStatement(sql);

        //THEN
        verify(jdbcController).handleExecute(sql);
        Assert.assertEquals(dataSet, this.dataSet);
    }

    @Test
    public void testExecuteUpdateDelegatesToHandleUpdate(){
        //WHEN
        final int affectedRows = jdbcController.executeUpdate(sql);

        //THEN
        verify(jdbcController).handleUpdate(sql);
        Assert.assertEquals(affectedRows, this.affectedRows);
    }
}