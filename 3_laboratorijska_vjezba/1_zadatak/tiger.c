#include<stdio.h>
#include<stdlib.h>

typedef const char* (*PTRFUN)();

struct Tiger
{
    PTRFUN* vtable;
    const char* ime;
};

const char* name(void* animal) {
    return ((struct Tiger*)animal)->ime;
}

const char* greet() {
    return "rauuu";
}

const char* menu() {
    return "papagaje";
}

PTRFUN tiger_table[3] = {*name, *greet, *menu};

void* create(const char* ime) {
    struct Tiger* tiger = (struct Tiger*)malloc(sizeof(struct Tiger));

    tiger->ime = ime;
    tiger->vtable = tiger_table;

    return tiger;
}
