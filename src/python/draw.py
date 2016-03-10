import sys
import matplotlib.pyplot as plt
import numpy as np
import StringIO


def draw_figure(X, Ys, labels, styles, xlabel='x', ylabel='y', y_range=(0,)):
    fig = plt.figure()
    for lb, stl in zip(labels, styles):
        plt.plot(X, Ys[lb], stl, label=lb)

    legend = plt.legend(loc='upper right', shadow=True)
    plt.ylabel(ylabel)
    plt.xlabel(xlabel)

    plt.ylim(*y_range)
    plt.show()
