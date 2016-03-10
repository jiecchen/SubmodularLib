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


def draw_errorbar(X, Ys, labels, styles,  xlabel='x', ylabel='y'):
    plt.figure()

    # draw pictures
    for label, style in zip(labels, styles):
        Y = Ys[label]
        if isinstance(Y, list):
            errors = [2. * np.std(y) for y in Y]
            means = [np.mean(y) for y in Y]
        else:
            errors = [0] * len(Y)
            means = Y
        plt.errorbar(X, means, errors, fmt=style, label=label)

    legend = plt.legend(loc='upper right', shadow=True)
    plt.xlim((0, max(X) * 1.2))
    plt.xlabel(xlabel)
    plt.ylabel(ylabel)    
    plt.show()

if __name__ == '__main__':
    # test

    labels = ['test1', 'test2']    
    styles = ['r-o', 'b-o']
    X = [1, 2, 3]
    Ys = {}
    Ys['test1'] = [[1.9, 2, 2], [5, 4.7, 5], [3, 2.7, 3.1]]
    Ys['test2'] = [1, 2, 3]
    draw_errorbar(X, Ys, labels, styles)

