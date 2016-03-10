import sys
import matplotlib.pyplot as plt
import numpy as np
import StringIO 
import draw


nSets = 500
ks =  [5, 10, 15, 20, 25, 30, 35, 40, 45]

labels = ['Greedy', 'GreedyLazy', 'StocGreedy']

nQueries = {}
nQueries[labels[0]] = [nSets * a for a in ks]
nQueries[labels[1]] = [1009, 1008, 1013, 1018, 1023, 1028, 1033, 1038, 1043]
nQueries[labels[2]] = [696, 573, 538, 505, 501, 492, 498, 489, 481]

values = {}
values[labels[0]] = []
values[labels[1]] = []
values[labels[2]] = []




# draw the graph
styles = ['g-x', 'r-^', 'b-s']

#legend.get_frame().set_facecolor('#00FFCC')

ylabel = r'number of value queries'
xlabel = r'k - cardinality constraint'

draw.draw_figure(ks, nQueries, labels, styles, xlabel, ylabel, (0, 10000))

