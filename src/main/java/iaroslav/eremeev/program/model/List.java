package iaroslav.eremeev.program.model;

import java.lang.reflect.Array;
import java.util.Map;
import java.util.Objects;

public class List<T> {
    class Entry{
        private T value;
        private Entry next;
        private Entry prev;

        public Entry(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return (String) value;
        }
    }
    private int size;
    private Entry last;
    private Entry first;
    private Entry current;

    public List() {
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public Entry get(int index){
        if (index >= size || index < 0) return null;
        else if (index == 0) return this.first;
        else {
            this.current = this.first;
            for (int i = 1; i <= index; i++) {
                this.current = this.current.next;
            }
            return this.current;
        }
    }

    public boolean add(T value){
        if (isEmpty()){
            this.last = new Entry(value);
            this.first = this.last;
        }
        else {
            Entry last = this.last;
            this.last.next = new Entry(value);
            this.last = this.last.next;
            this.last.prev = last;
        }
        this.size++;
        return true;
    }

    public boolean add(int index, T value){
        if (index < 0 || index > this.size) return false;
        else if (index == 0 && isEmpty()) {
            add(value);
            this.size++;
        }
        else if (index == 0) {
            Entry firstSaved = this.first;
            this.first = new Entry(value);
            this.first.next = firstSaved;
            this.first.next.prev = this.first;
            this.size++;
        }
        else {
            this.current = this.first;
            for (int i = 0; i < index - 1; i++) {
                this.current = this.current.next;
            }
            Entry beforeIndex = this.current;
            Entry atIndex = this.current.next;
            Entry newEntry = new Entry(value);
            this.current.next = newEntry;
            this.current = this.current.next;
            this.current.prev = beforeIndex;
            this.current.next = atIndex;
            this.size++;
        }
        return true;
    }

    public int indexOf (T value){
        this.current = this.first;
        if (Objects.equals(this.current.value, value)) return 0;
        int index = 0;
        while (!Objects.equals(this.current.value, value)){
            index++;
            if (index == this.size) return -1;
            this.current = this.current.next;
        }
        return index;
    }

    public boolean contains(T value){
        this.current = this.first;
        if (Objects.equals(this.current.value, value)) return true;
        while (!Objects.equals(this.current.value, value)){
            this.current = this.current.next;
            if (this.current == null) return false;
        }
        return true;
    }

    public Entry remove(int index){
        if (index < 0 || index >= this.size) return null;
        else if (index == 0) {
            Entry atIndex = this.first;
            if (this.first.next != null){
                this.first = this.first.next;
                this.first.prev = null;
            }
            this.size--;
            return atIndex;
        }
        else {
            this.current = this.first;
            for (int i = 0; i < index - 1; i++) {
                this.current = this.current.next;
            }
            Entry beforeIndex = this.current;
            Entry atIndex = this.current.next;
            Entry afterIndex = this.current.next.next;
            this.current.next = afterIndex;
            this.current = this.current.next;
            if (this.current != null) this.current.prev = beforeIndex;
            this.size--;
            return atIndex;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(get(i));
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public void clear(){
        while (!isEmpty()) remove(0);
    }


}
