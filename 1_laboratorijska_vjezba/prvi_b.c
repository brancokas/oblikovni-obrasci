#include<stdio.h>
#include<stdlib.h>
#include<string.h>

typedef char const* (*PTRFUN)();

char const* dogGreet(void){
  return "vau!";
}
char const* dogMenu(void){
  return "kuhanu govedinu";
}
char const* catGreet(void){
  return "mijau!";
}
char const* catMenu(void){
  return "konzerviranu tunjevinu";
}

struct Animal_vtable {
    const char* (*greet)();
    const char* (*menu)();
};

const struct Animal_vtable DOG[] = {*dogGreet, *dogMenu}, CAT[] = {*catGreet, *catMenu};

struct Animal
{
    const char* ime;
    struct Animal_vtable* vtable_;
};

void animalPrintGreeting(struct Animal* animal) {
    PTRFUN fun = animal->vtable_->greet;
    printf("%s pozdravlja: %s\n", animal->ime, fun());
}

void animalPrintMenu(struct Animal* animal) {
    PTRFUN fun = animal->vtable_->menu;
    printf("%s voli: %s.\n", animal->ime, fun());
}

void constructDog(struct Animal* animal, char const* ime) {
    animal->ime = ime;
    animal->vtable_ = DOG;
}

struct Animal* createDog(char const* ime) {
    struct Animal* dog = (struct Animal*)malloc(sizeof(struct Animal));
    constructDog(dog, ime);
    return dog;
}

void constructCat(struct Animal* animal, char const* ime) {
    animal->ime = ime;
    animal->vtable_ = CAT;
}

struct Animal* createCat(char const* ime) {
    struct Animal* cat = (struct Animal*)malloc(sizeof(struct Animal));
    constructCat(cat, ime);
    return cat;
}

const char* newDog(int i) {
    char* num;
    char buffer[100];

    if (asprintf(&num, "%d", i) == -1) {
        perror("asprintf");
    } else {
        strcat(strcpy(buffer, "Pesek_"), num);
        free(num);
    }

    char* str = malloc(sizeof buffer);    
    int j = 0;
    while(buffer[j] != '\0') {
        str[j] = buffer[j];
        j++;
    }

    return str;
}

struct Animal* createNDogs(int n) {
    struct Animal* dogs = (struct Animal*)malloc(n * sizeof(struct Animal));
    for (int i = 0; i < n; i++) {
        const char* dogName = newDog(i+1);
        constructDog(dogs + i, dogName);
    }
    return dogs;
}


void testAnimals(void) {
    struct Animal* p1=createDog("Hamlet");
    struct Animal* p2=createCat("Ofelija");
    struct Animal* p3=createDog("Polonije");
    struct Animal dog;

    animalPrintGreeting(p1);
    animalPrintGreeting(p2);
    animalPrintGreeting(p3);

    animalPrintMenu(p1);
    animalPrintMenu(p2);
    animalPrintMenu(p3);

    free(p1);
    free(p2); 
    free(p3);
}

int main(void) {
    testAnimals();
    struct Animal* dogs = createNDogs(10);
    for (int i = 0; i < 10; i++)
    {
        animalPrintMenu(dogs + i);
    }
    
    free(dogs);
    return 0;
}