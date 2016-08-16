from __future__ import print_function

import sys
from operator import add

from pyspark.sql import SparkSession

def getKey(item):
       return item[1]

   if __name__ == "__main__":
          if len(sys.argv) != 2:
                     print("Usage: wordcount <file>", file=sys.stderr)
                            exit(-1)

                               spark = SparkSession\
                                              .builder\
                                                     .appName("PythonWordCount")\
                                                            .getOrCreate()

                                                               lines = spark.read.text(sys.argv[1]).rdd.map(lambda r: r[0])
                                                                  counts = lines.flatMap(lambda x: x.split(' ')) \
                                                                                           .map(lambda x: (x, 1)) \
                                                                                                            .reduceByKey(add)
                                                                                                               output = counts.collect()
                                                                                                                      

                                                                                                                         sorted_output = sorted(output, key = getKey, reverse = True)
                                                                                                                            for (word, count) in sorted_output:
                                                                                                                                       print("%s: %i" % (word, count))

                                                                                                                                          spark.stop()
