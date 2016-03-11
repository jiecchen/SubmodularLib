import sys
import matplotlib.pyplot as plt
import numpy as np
import StringIO 
import draw


nSets = 1000
ks =  [5, 10, 15, 20, 25, 30, 35, 40, 45]

labels = ['Greedy', 'GreedyLazy', 'StocGreedy']

nQueries = {}
nQueries[labels[0]] = [nSets * a for a in ks]
nQueries[labels[1]] = [2048, 2053, 2058, 2063, 2068, 2073, 2078, 2083, 2088]
nQueries[labels[2]] = [696, 573, 538, 505, 501, 492, 498, 489, 481]

values = {}
values[labels[0]] = [256, 438, 602, 668, 756, 828, 848, 890, 925]
values[labels[1]] = [305, 466, 648, 752, 828, 871, 900, 926, 946]
values[labels[2]] = [[105, 149, 157, 110, 151], [171, 258, 246, 215, 275], [402, 368, 371, 384, 451], [604, 546, 542, 613, 623], [643, 637, 679, 707, 713], [806, 801, 783, 778, 749], [849, 864, 862, 817, 859], [858, 853, 871, 883, 886], [900, 917, 904, 885, 885]]




# draw the graph
styles = ['g-x', 'r-^', 'b-s']

#legend.get_frame().set_facecolor('#00FFCC')

ylabel = r'number of value queries'
xlabel = r'k - cardinality constraint'

draw.draw_figure(ks, nQueries, labels, styles, xlabel, ylabel, (0, 10000))

ylabel = r'f(S) - value of function'
xlabel = r'k - cardinality constraint'
draw.draw_errorbar(ks, values, labels, styles, xlabel, ylabel)

