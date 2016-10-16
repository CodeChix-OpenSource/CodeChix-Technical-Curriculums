#!/usr/local/bin/python
from pyspark.sql import SparkSession

def basic_example(spark):
	df = spark.read.json("/Users/lyujiazhang/Documents/SparkSql/employee.json")
	# Print out dataframe
	df.show()
	# Print out attribute types
	df.printSchema()
	# SQL quries on the dataframe
	df.select("name").show()
	df.groupBy("age").count().show()
	# Register the DataFrame as a SQL temporary view
	df.createOrReplaceTempView("employee")
	# SQL quries on the schema
	youngerthan30 = spark.sql("SELECT * FROM employee WHERE age < 30")
	# rdd returns the content as an :class:`pyspark.RDD` of :class:`Row`
	Names = youngerthan30.rdd.map(lambda p: "Name: " + p.name).collect()
	for name in Names:
		print(name)

if __name__ == "__main__":
    spark = SparkSession \
        .builder \
        .appName("Python Spark SQL dataframe example") \
        .config("spark.some.config.option", "some-value") \
        .getOrCreate()

    basic_example(spark)
    spark.stop()