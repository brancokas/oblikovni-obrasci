#include<stdio.h>
#include<stdlib.h>

struct Unary_Function_vtable
{
    double (*value_at)(void *, double);
    double (*negative_value_at)(void *, double);
};

struct Unary_Function
{
    struct Unary_Function_vtable* vtable;
    int lower_bound;
    int upper_bound;
};

void tabulate(struct Unary_Function* fun) {
    for(int x = fun->lower_bound; x <= fun->upper_bound; x++) {
        printf("f(%d)=%lf\n", x, fun->vtable->value_at(fun, x));
      }
}

int same_functions_for_ints(struct Unary_Function *f1, struct Unary_Function *f2, double tolerance) {
      if(f1->lower_bound != f2->lower_bound) return 0;
      if(f1->upper_bound != f2->upper_bound) return 0;
      for(int x = f1->lower_bound; x <= f1->upper_bound; x++) {
        double delta = f1->vtable->value_at(f1, x) - f2->vtable->value_at(f2, x);
        if(delta < 0) delta = -delta;
        if(delta > tolerance) return 0;
      }
      return 1;
    };

struct Square
{
    struct Unary_Function super;
};

struct Linear
{
    struct Unary_Function super;
    double a;
    double b;
};


double negative_value_at(struct Unary_Function* fun, double x) {
    return -fun->vtable->value_at(fun, x);
}

double value_at_square(struct Unary_Function* fun, double x) {
    return x*x;
}

double value_at_linear(struct Unary_Function* fun, double x) {
    struct Linear* linear = (struct Linear*)(fun);
    return linear->a * x + linear->b;
}


const struct Unary_Function_vtable squareTable[] = {*value_at_square, *negative_value_at};
const struct Unary_Function_vtable linearTable[] = {*value_at_linear, *negative_value_at};

void constructSquare(struct Square* square, double lb, double ub) {
    square->super.lower_bound = lb;
    square->super.upper_bound = ub;
    square->super.vtable = squareTable;

}

void constructLinear(struct Linear* linear, double lb, double ub, double x, double y) {
    linear->a = x;
    linear->b = y;
    linear->super.lower_bound = lb;
    linear->super.upper_bound = ub;
    linear->super.vtable = linearTable;
}

struct Unary_Function* create_square_function(double lb, double ub) {
    struct Square* square = (struct Square*)malloc(sizeof(struct Square));
    constructSquare(square, lb, ub);
    return (struct Unary_Function*)square;
}

struct Unary_Function* create_linear_function(double lb, double up, double x, double y) {
    struct Linear* linear = (struct Linear*)malloc(sizeof(struct Linear));
    constructLinear(linear, lb, up, x, y);
    return (struct Unary_Function*)linear;
}

int main(void) {
    struct Unary_Function* fun1 = create_square_function(-2,2);
    struct Unary_Function* fun2 = create_linear_function(-2,2,5,-2);
    
    tabulate(fun1);
    tabulate(fun2);

    int istina = same_functions_for_ints(fun1, fun2, 1E-6);
    if (istina > 0)
    {
        printf("f1==f2: DA\n");
    } else {
        printf("f1==f2: NE\n");
    }

    printf("neg_val f2(1) = %lf\n", fun2->vtable->negative_value_at(fun2,1.0));



    free(fun1);
    free(fun2);
    return 0;
}