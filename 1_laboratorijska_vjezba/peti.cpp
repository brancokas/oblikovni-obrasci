#include<stdio.h>
#include<stdlib.h>

class B{
public:
  virtual int prva()=0;
  virtual int druga(int)=0;
};

class D: public B{
public:
  virtual int prva(){return 42;}
  virtual int druga(int x){return prva()+x;}
};

void funkcija(B* pb) {
    size_t* vtable = *((size_t**)pb);

    int (*fun1)(B*) = (int(*)(B*))*(vtable);
    int (*fun2)(B*, int) = (int(*)(B*, int))vtable[1];

    printf("%d\n",fun1(pb));
    printf("%d\n",fun2(pb, 10));
}

int main() {
    B* pb = new D();
    funkcija(pb);
    return 0;
}