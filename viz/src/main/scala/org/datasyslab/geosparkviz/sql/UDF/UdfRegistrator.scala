/*
 * FILE: UdfRegistrator.scala
 * Copyright (c) 2015 - 2019 GeoSpark Development Team
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
package org.datasyslab.geosparkviz.sql.UDF

import org.apache.spark.sql.catalyst.analysis.FunctionRegistry
import org.apache.spark.sql.{SQLContext, SparkSession}

object UdfRegistrator {

  def registerAll(sqlContext: SQLContext): Unit = {
    registerAll(sqlContext.sparkSession)
  }

  def registerAll(sparkSession: SparkSession): Unit = {
    Catalog.expressions.foreach(f=>FunctionRegistry.builtin.registerFunction(f.getClass.getSimpleName.dropRight(1),f))
    Catalog.aggregateExpressions.foreach(f=>FunctionRegistry.builtin.registerFunction(f.getClass.getSimpleName,f))
  }

  def dropAll(sparkSession: SparkSession): Unit = {
    Catalog.expressions.foreach(f=>FunctionRegistry.builtin.dropFunction(f.getClass.getSimpleName.dropRight(1)))
    Catalog.aggregateExpressions.foreach(f=>FunctionRegistry.builtin.dropFunction(f.getClass.getSimpleName))
  }
}
