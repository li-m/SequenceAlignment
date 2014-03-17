SequenceAlignment
=================
This is a program written in Java.

Given a web log where customers’ purchasing histories are recorded, the program generalize the regularity of the customers’ purchasing, and to find out possible unusual behaviours.

To do that, firstly the method of sequence alignment is used to calculate the fluctuations of purchasing history in time dimension, then control charts, namely EWMA (exponentially-weighted moving average) chart and CUSUM (cumulative sum) chart, are introduced to detect possible exceptions.


The entry is at ***SequenceAlignment/src/sequenceAlignment/Entry.java*** and main procedures is executed in ***SequenceAlignment/src/sequenceAlignment/Procedures.java***
