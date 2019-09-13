//;==========================================
//; Title:  FLAMES game
//; Author: Mohamed Farhath Manas
//; Date:   13 Sep 2019
//;==========================================

#include<stdio.h>
#include<stdlib.h>
#include<string.h>

struct flames {
    struct flames *prev;
    char c;
    struct flames *next;
};

struct flames *start = NULL;
struct flames *end = NULL;

void createFlames();

void deleteChar();

void traverse();

int main() {
    char *s1 = malloc(sizeof(char));
    char *s2 = malloc(sizeof(char));

    int *cS1 = (int *) calloc(26, sizeof(int));
    int *cS2 = (int *) calloc(26, sizeof(int));

    int i, j, count = 0;

    printf("******** Welcome to FLAMES game ********");
    printf("\n");
    printf("-------- ====================== --------");
    printf("\n");

    printf("Enter 1st person's name: ");
    fgets(s1, 256, stdin);
    printf("Enter 2nd person's name: ");
    fgets(s2, 256, stdin);

    j = 0;
    for (i = 0; i < (strlen(s1) - 1); ++i) {
        int t = (int) s1[i];
        if (t >= 65 && t <= 90) j = t - 65;
        else if (t >= 97 && t <= 122) j = t - 97;
        else continue;
        cS1[j]++;
    }

    j = 0;
    for (i = 0; i < (strlen(s2) - 1); ++i) {
        int t = (int) s2[i];
        if (t >= 65 && t <= 90) j = t - 65;
        else if (t >= 97 && t <= 122) j = t - 97;
        else continue;
        cS2[j]++;
    }

    j = 0;
    for (i = 0; i < 26; ++i) {
        int t = cS1[i] - cS2[i];
        if (t < 0)
            t *= -1;
        count += t;
    }

    struct flames *temp = NULL;
    int iterFlames = 5, tempIter;
    createFlames();
    temp = start;

    while (iterFlames-- > 0) {
        tempIter = 0;
        while (tempIter++ < (count - 1))
            traverse();
        deleteChar();
    }

    switch (start->c) {
        case 'f':
            printf("*** Friends ***");
            break;
        case 'l':
            printf("*** Love ***");
            break;
        case 'a':
            printf("*** Affection ***");
            break;
        case 'm':
            printf("*** Marriage ***");
            break;
        case 'e':
            printf("*** Enemy ***");
            break;
        case 's':
            printf("*** Sister ***");
            break;
    }
    return 0;
}

void createFlames() {
    char ch[6] = {'f', 'l', 'a', 'm', 'e', 's'};
    int i;

    for (i = 0; i < 6; ++i) {
        struct flames *temp = (struct flames *) malloc(sizeof(struct flames));
        temp->c = ch[i];
        temp->prev = NULL;
        temp->next = NULL;

        if (start == NULL) {
            start = end = temp;
            continue;
        }
        end->next = temp;
        temp->prev = end;
        end = end->next;
    }
    end->next = start;
    start->prev = end;
}

void deleteChar() {
    struct flames *temp = start;
    end->next = end->next->next;
    start = start->next;
    start->prev = end;
    free(temp);
}

void traverse() {
    start = start->next;
    end = end->next;
}