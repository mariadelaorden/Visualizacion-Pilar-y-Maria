package es.uah.matcomp.proyecto.cod.grafo;

import es.uah.matcomp.proyecto.cod.listas.ListaSimple;

public class Mapa<K, V> {
    private ListaSimple<Entry<K, V>>[] contenedores;
    private int capacidad=1000;

    public Mapa() {
        contenedores = new ListaSimple[capacidad];
        for (int i = 0; i < capacidad; i++) {
            contenedores[i] = new ListaSimple<>(capacidad);
        }
    }

    public void put(K key, V value) {
        int index = getIndice(key);
        ListaSimple<Entry<K, V>> bucket = contenedores[index];
        Entry<K, V> newEntry = new Entry<>(key, value);
        bucket.add(newEntry);
    }

    public V get(K key) {
        int index = getIndice(key);
        ListaSimple<Entry<K, V>> bucket =  contenedores[index];
        for (int i = 0; i < bucket.getNumeroElementos(); i++) {
            if (bucket.getElemento(i) != null) {
                Entry<K, V> entry = (Entry<K, V>) bucket.getElemento(i).getData();
                if (entry != null && entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }
    public ListaSimple<K> keySet() {
        ListaSimple<K> keys = new ListaSimple<>(1000);
        for (ListaSimple<Entry<K, V>> bucket : contenedores) {
            for (int i = 0; i < bucket.getNumeroElementos(); i++) {
                Entry<K, V> entry = (Entry<K, V>) bucket.getElemento(i).getData();
                if (entry != null) {
                    keys.add(entry.getKey());
                }
            }
        }
        return keys;
    }

    private int getIndice(K key) {
        return Math.abs(key.hashCode() % capacidad);
    }
    public ListaSimple<V> values() {
        ListaSimple<V> values = new ListaSimple<>(1000);
        for (ListaSimple<Entry<K, V>> bucket : contenedores) {
            for (int i = 0; i < bucket.getNumeroElementos(); i++) {
                Entry<K, V> entry = (Entry<K, V>) bucket.getElemento(i).getData();
                if (entry != null) {
                    values.add(entry.getValue());
                }
            }
        }
        return values;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        ListaSimple<K> keys = this.keySet();
        for (int i = 0; i < keys.getNumeroElementos(); i++) {
            K key = (K) keys.getElemento(i);
            sb.append(key.toString()).append("=").append(this.get(key)).append(", ");
        }
        // Elimina la coma y el espacio al final
        if (sb.length() > 1) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("}");
        return sb.toString();
    }

    public boolean containsKey(K key) {
        int index = getIndice(key);
        ListaSimple<Entry<K, V>> bucket = contenedores[index];
        for (int i = 0; i < bucket.getNumeroElementos(); i++) {
            if (bucket.getElemento(i) != null) {
                Entry<K, V> entry = (Entry<K, V>) bucket.getElemento(i).getData();
                if (entry != null && entry.getKey().equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }


    static class Entry<K, V> {
        public void setKey(K key) {
            this.key = key;
        }

        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}