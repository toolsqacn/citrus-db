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

package com.consol.citrus.db.driver.data;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Christoph Deppisch
 */
public class Row {

    /** Row values with column name as key */
    private Map<String, String> values = new LinkedHashMap<>();

    /**
     * Gets set of column names available in this row.
     * @return
     */
    public List<String> getColumns() {
        return Arrays.asList(values.keySet().toArray(new String[values.size()]));
    }

    /**
     * Gets the row value identified by its column name.
     * @param columnName
     * @return
     */
    public String getValue(String columnName) {
        return values.get(columnName);
    }

    /**
     * Gets the row value identified by its column index.
     * @param columnIndex
     * @return
     */
    public String getValue(int columnIndex) {
        return values.values().toArray(new String[values.size()])[columnIndex];
    }

    /**
     * Gets the values.
     *
     * @return
     */
    public Map<String, String> getValues() {
        return values;
    }

    /**
     * Sets the values.
     *
     * @param values
     */
    public void setValues(Map<String, String> values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Row row = (Row) o;
        return Objects.equals(values, row.values);
    }

    @Override
    public int hashCode() {

        return Objects.hash(values);
    }

    @Override
    public String toString() {
        return "Row{" +
                "values=" + values +
                '}';
    }
}
