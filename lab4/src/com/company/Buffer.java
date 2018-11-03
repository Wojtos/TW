package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Buffer implements Iterable<Cell> {
    final private Integer length;
    final private List<Cell> cells;


    public Buffer(Integer length, Integer maxValue) {
        this.length = length;
        this.cells = new ArrayList<>(length);
        for (int i = 0; i < this.length; i++) {
            cells.add(new Cell(i, maxValue));
        }
    }

    public Iterator<Cell> iterator() {
        return new Iterator<>() {
            private Integer index = -1;
            public Cell next() {
                this.index = (this.index + 1) % length;
                return cells.get(this.index);
            }
            public boolean hasNext(){
                return true;
            }
        };
    }

    public Cell nextCell(Integer index) {
        return this.cells.get((index + 1) % this.length);
    }
}
