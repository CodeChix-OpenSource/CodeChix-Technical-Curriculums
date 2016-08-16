#!/usr/local/bin/python
from pyspark.sql import SparkSession

def datasource_solution(spark):
    peopleDF = spark.read.json("/Users/lyujiazhang/Documents/SparkSql/employee.json")
    peopleDF.write.parquet("employee.parquet")
    parquetFile = spark.read.parquet("employee.parquet")
    parquetFile.createOrReplaceTempView("parquetFile")
    youngerthan30 = spark.sql("SELECT name FROM parquetFile WHERE age < 30")
    youngerthan30.show()

if __name__ == "__main__":
    spark = SparkSession \
        .builder \
        .appName("Python Spark SQL datasource example") \
        .config("spark.some.config.option", "some-value") \
        .getOrCreate()

    datasource_solution(spark)

    spark.stop()