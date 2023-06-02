#include<dlfcn.h>
#include<string.h>
#include<stdio.h>
void* myfactory(char const* libname, char const* ctorarg) {
    // fullName = ./ + libname + .so + '\0';
    char fullName[2 + strlen(libname) + 3 + 1];

    strcat(fullName, "./");
    strcat(fullName, libname);
    strcat(fullName, ".so");

    void* handle = dlopen(fullName, RTLD_LAZY);

    if (!handle) {
        printf("Greska pri stvaranju %s\n", fullName);
        return NULL;
    }

    void* (*method_ptr)(char const*);

    method_ptr = (void* (*)(char const*))dlsym(handle, "create");
    
    return (*method_ptr)(ctorarg);
}