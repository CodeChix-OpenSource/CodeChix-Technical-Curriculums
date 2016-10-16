# Copyright (c) Shubhi Rani, Stony Brook University
# Copyright (c) Sejal Chauhan, University of Wisconsin Madisson
# Copyright (c) Wan Chen, University of Illinois, Urbana Champaign 
# Copyright (c) Lyujia Zhang, Columbia University
# CopyRight (c) CodeChix, VmWare

from pyspark import SparkContext
from pyspark.mllib.classification import SVMWithSGD, SVMModel
from pyspark.mllib.regression import LabeledPoint

if __name__ == "__main__":
    sc = SparkContext(appName="SVMTicTac")

    # Parse the data and create LabeledPoints
    def parsePoint(line):
        values = [(x) for x in line.split(' ')]
        # Last row contains the target data and rest of
        # the rows define the attributes for linear regression
        return LabeledPoint(values[9], values[0:8])

    # Load the data
    data = sc.textFile("data/mllib/sample_traindata_tic_tac.txt")
    parsedData = data.map(parsePoint)

    # Build the model using SVD
    model = SVMWithSGD.train(parsedData, iterations=100)

    # Evaluating the model on training data
    predict_model = parsedData.map(lambda p: (p.label, model.predict(p.features)))
    trainErr = predict_model.filter(lambda (v, p): v != p).count() / float(parsedData.count())
    
    # Print Mean Squared Error
    print("Training Error = " + str(trainErr))

    # Save and load model
    model.save(sc, "target/tmp/pythonTicTacSGD")
    sameModel = SVMModel.load(sc, "target/tmp/pythonTicTacSGD")