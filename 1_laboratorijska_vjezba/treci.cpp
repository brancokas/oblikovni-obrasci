#include<stdio.h>
#include<stdlib.h>

class CoolClass{
public:
  virtual void set(int x){};
  virtual int get(){return 0;};
  int c() {return 0;};
  int a[3];
};
class PlainOldClass{
public:
  void set(int x){x_=x;};
  int get(){return x_;};
private:
  int x_;
  int a[3];
};
int main() {
    CoolClass* ccObjekt = new CoolClass();
    PlainOldClass* pocObjekt = new PlainOldClass();
    CoolClass cc;
    PlainOldClass poc;

    printf("Cool Class Objekt = %lu\n", sizeof(ccObjekt));
    printf("Plain Old Class Objekt = %lu\n", sizeof(pocObjekt));

    printf("Cool Class = %lu\n", sizeof(cc));
    printf("Plain Old Class = %lu\n", sizeof(poc));

    free(ccObjekt);
    free(pocObjekt);
    return 0;

}  
