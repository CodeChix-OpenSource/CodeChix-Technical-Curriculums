# Copyright (c) Shubhi Rani 
# Copyright (c) Sejal Chauhan
# Copyright (c) Wan Chen
# Copyright (c) Lyujia Zhang
# CopyRight (c) CodeChix

"""
Learn Linear Regression using SGD.
"""

from __future__ import print_function
from pyspark import SparkContext
from pyspark.mllib.regression import LabeledPoint, LinearRegressionWithSGD, LinearRegressionModel

if __name__ == "__main__":

    sc = SparkContext(appName="TicTacLinearRegressionExample")

    # Parse the data and create LabeledPoints
    def parsePoint(line):
        values = [x for x in line.split(' ')]
        # Last row contains the target data and rest of
        # the rows define the attributes for linear regression
        return LabeledPoint(values[9], values[0:8])

    # Load the data
    data = sc.textFile("data/mllib/sample_traindata_tic_tac.txt")
    parsedData = data.map(parsePoint)

    # Build the model using LinearRegression
    model = LinearRegressionWithSGD.train(parsedData, iterations=100, step=0.00000001)

    # Evaluate the model on training data
    predict = parsedData.map(lambda pd: (pd.label, model.predict(pd.features)))
    MSE = predict \
        .map(lambda (v, p): (v - p)**2) \
        .reduce(lambda x, y: x + y) / predict.count()

    # Print Mean Squared Error
    print("Mean Squared Error for Tic Tac Linear Regression = " + str(MSE))

    # Save and load model
    model.save(sc, "target/tmp/pythonTicTacLinearRegression")
    sameModel = LinearRegressionModel.load(sc, "target/tmp/pythonTicTacLinearRegression")