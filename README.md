Algorytm ewolucyjny z populacją rosnącą w nieskończoność
====
Kompilacja niezbędnej biblioteki `cjavabbob`:

```
cd bbob.v13.09/java/generatelib/
make
```

Kompilacja kodu Javy znajdującego się w `src` przy pomocy kompilatora Eclipse. 
Wystarczy otworzyć projekt w Eclipse, kompilator zostanie uruchomiony automatycznie i wygeneruje katalog `bin`.

Uruchomienie:

```
./run.sh [algorytm=ae] [wymiar=10]
```
Na przykład:
```
./run.sh naek 40
```

Dostępne algorytmy:
- `ae` - Klasyczny algorytm ewolucyjny.
- `naeu` - NAE (algorytm ewolucyjny z populacją rosnącą w nieskończoność) z rozkładem jednostajnym.
- `naep` - NAE z rozkładem o pierwiastkowej funkcji gęstości p-stwa.
- `nael` - NAE z rozkładem o liniowej funkcji gęstości p-stwa.
- `naek` - NAE z rozkładem o kwadratowej funkcji gęstości p-stwa.
