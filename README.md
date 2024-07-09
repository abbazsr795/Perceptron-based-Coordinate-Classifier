# Perceptron-based Coordinate Classifier.iml
This Java project implements a Perceptron algorithm to classify whether a given point lies above or below a predefined linear boundary defined by the equation y=mx+c with zero machine learning frameworks. The classifier is trained using a set of labeled coordinates, and it predicts the position of new coordinates relative to the line.
## Features
- Perceptron Training: Implements the Perceptron learning algorithm to adjust weights based on training data.
- Random Weight Initialization: Initializes weights randomly within a specified range.
- Interactive Visualization: Displays coordinates and the decision boundary on a Java Swing GUI.
## Usage
### 1.Training the Perceptron
- The training data consists of a set of (x, y) coordinates and their labels (1 for above the line, -1 for below the line).
- The Perceptron adjusts its weights over several iterations to minimize classification errors.
### 2. Running the Classifier
- The GUI displays the coordinates and the decision boundary.
- The GUI displays the predicted decision boundary used and the respective coordinates it got correct (colour green) and wrong (colour red).
- Loops until all coordinates are predicted correctly

