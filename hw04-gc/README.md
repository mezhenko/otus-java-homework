# Ivan Mezhenko
### h04-gc
##### До изменений

256m heap, 5 запусков: 
 * spend msec:10833, sec:10
 * spend msec:14931, sec:14
 * spend msec:11494, sec:11
 * spend msec:11475, sec:11
 * spend msec:11870, sec:11

2046m heap, 5 запусков —
 * spend msec:11148, sec:11
 * spend msec:11676, sec:11
 * spend msec:12043, sec:12
 * spend msec:12941, sec:12
 * spend msec:13454, sec:13

124m heap, 5 запусков —
 * spend msec:11770, sec:11
 * spend msec:14607, sec:14
 * spend msec:12089, sec:12
 * spend msec:12087, sec:12
 * spend msec:12358, sec:12

96m heap, 5 запусков —
 * spend msec:13080, sec:13
 * spend msec:18761, sec:18
 * spend msec:15511, sec:15
 * spend msec:13826, sec:13
 * spend msec:16909, sec:16

64m heap, 5 запусков —
 * spend msec:14459, sec:14
 * spend msec:12546, sec:12
 * spend msec:12735, sec:12
 * spend msec:13524, sec:13
 * spend msec:14561, sec:14

48m heap, 5 запусков —
 * spend msec:12486, sec:12
 * heap space is exhausted
 * spend msec:14488, sec:14
 * heap space is exhausted
 * spend msec:15069, sec:15


Таким образом ярких изменений во времени работы нет, 
в районе размера хипа 124m происходит падение на пару ms 


##### После изменений

256m heap, 5 запусков:
 * spend msec:7673, sec:7
 * spend msec:7791, sec:7
 * spend msec:7846, sec:7
 * spend msec:7667, sec:7
 * spend msec:7710, sec:7

2046m heap, 5 запусков —
* spend msec:7881, sec:7
* spend msec:7623, sec:7
* spend msec:7838, sec:7
* spend msec:7518, sec:7
* spend msec:7621, sec:7

124m, 5 запусков
 * spend msec:7687, sec:7
 * spend msec:7529, sec:7
 * spend msec:7807, sec:7
 * spend msec:7546, sec:7
 * spend msec:7708, sec:7

96m heap, 5 запусков —
 * spend msec:7832, sec:7
 * spend msec:7847, sec:7
 * spend msec:8232, sec:8
 * spend msec:8411, sec:8
 * spend msec:8120, sec:8

64m heap, 5 запусков —
 * spend msec:7536, sec:7
 * spend msec:7772, sec:7
 * spend msec:7986, sec:7
 * spend msec:7922, sec:7
 * spend msec:7693, sec:7

48m, 5 запусков
 * spend msec:7523, sec:7
 * spend msec:7481, sec:7
 * spend msec:7635, sec:7
 * spend msec:7631, sec:7
 * spend msec:7547, sec:7


##### После перехода на примитивы

256m heap, 5 запусков:
* spend msec:975, sec:0
* spend msec:774, sec:0
* spend msec:819, sec:0
* spend msec:781, sec:0
* spend msec:800, sec:0

2046m heap, 5 запусков —
* spend msec:825, sec:0
* spend msec:791, sec:0
* spend msec:869, sec:0
* spend msec:845, sec:0
* spend msec:810, sec:0

124m heap, 5 запусков —
* spend msec:721, sec:0
* spend msec:794, sec:0
* spend msec:807, sec:0
* spend msec:864, sec:0
* spend msec:873, sec:0

96m heap, 5 запусков —
* spend msec:745, sec:0
* spend msec:782, sec:0
* spend msec:774, sec:0
* spend msec:868, sec:0
* spend msec:757, sec:0

64m heap, 5 запусков —
* spend msec:828, sec:0
* spend msec:826, sec:0
* spend msec:824, sec:0
* spend msec:813, sec:0
* spend msec:748, sec:0

48m heap, 5 запусков —
* spend msec:736, sec:0
* spend msec:850, sec:0
* spend msec:814, sec:0
* spend msec:773, sec:0
* spend msec:839, sec:0
