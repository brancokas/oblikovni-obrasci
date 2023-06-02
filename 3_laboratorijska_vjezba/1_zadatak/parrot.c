#include<stdio.h>
#include<stdlib.h>

typedef const char* (*PTRFUN)();

struct Parrot
{
    PTRFUN* vtable;
    const char* ime;
};

const char* name(void* animal) {
    return ((struct Parrot*)animal)->ime;
}

const char* greet() {
    return "gaga";
}

const char* menu() {
    return "tigrove";
}

PTRFUN parrot_table[3] = {*name, *greet, *menu};

void* create(const char* ime) {
    struct Parrot* parrot = (struct Parrot*)malloc(sizeof(struct Parrot));

    parrot->ime = ime;
    parrot->vtable = parrot_table;

    return parrot;
}
