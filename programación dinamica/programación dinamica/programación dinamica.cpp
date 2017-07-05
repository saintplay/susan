// programación dinamica.cpp : main project file.

#include "stdafx.h"
#include"stdio.h"
#include<vector>
#include<queue>

using namespace std;
typedef vector<int>monedas_existentes;
int moneda_a_cambiar,MAX=1000,valor_Acumulado;
monedas_existentes ME;

int ArrCambio[10000];
int ArrSolucion[10000];
int n, num_casos;

int INF = 1000000;

//int MIN() {
//	for (int i = 0;i < ME.size();i++) {
//		if(MAX>1 + DP(moneda_a_cambiar - ME[i]))
//		 MAX= 1 + DP(moneda_a_cambiar - ME[i]);
//	}
//	return MAX;
//}

int DP(int moneda_a_cambiar) {
	if (ArrCambio[moneda_a_cambiar] < INF)
		return ArrCambio[moneda_a_cambiar];

	int min = INF;
	int valorMoneda = 0;

	for (int i = 0;i < ME.size();i++) {
		int numCambio = 1 + DP(moneda_a_cambiar - ME[i]);

		if (numCambio < min)
		{
			valorMoneda = ME[i];
			min = numCambio;
		}
		/*
		if (MAX>1 + DP(moneda_a_cambiar - ME[i]))
			MAX = 1 + DP(moneda_a_cambiar - ME[i]);
			*/
	}
	ArrCambio[moneda_a_cambiar] = min;
	ArrSolucion[moneda_a_cambiar] = valorMoneda;
	

	return min;
}



int main()
{
	scanf("%d", &n);
	ME = monedas_existentes(n);
	for (int i = 0;i < n;i++) {
		scanf("%d", &ME[i]);
	}

	for (int i = 0; i < 100000; i++)
	{
		ArrCambio[i] = INF;
	}

	ArrCambio[0] = 0;

	scanf("%d", &num_casos);
	for (int i = 0;i < num_casos;i++) {
		scanf("%d", &moneda_a_cambiar);
		DP(moneda_a_cambiar);
		printf("%d",MAX);
	}

	//
	//4
	//1 5 10 20 
	//1
	//40

    return 0;
}
