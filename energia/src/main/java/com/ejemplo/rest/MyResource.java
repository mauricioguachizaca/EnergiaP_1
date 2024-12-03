package com.ejemplo.rest;

import java.util.Random;
import java.util.logging.Logger;
import java.util.logging.Level;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import controller.Dao.servicies.ProyectoServices;

@Path("/inversion")
public class MyResource {

    private static final Logger logger = Logger.getLogger(MyResource.class.getName());
    private static final Random random = new Random();

    @GET
    public void testSortingAndSearching() {
        ProyectoServices service = new ProyectoServices();

        int[] sizes = {10000, 20000, 25000};

        try {
            for (int size : sizes) {
                int[] array = generateRandomArray(size);

                long startQuickSort = System.nanoTime();
                service.order(1, "nombre");
                long endQuickSort = System.nanoTime();
                logTime("QuickSort", size, startQuickSort, endQuickSort);

                long startMergeSort = System.nanoTime();
                service.mergeSort(1, "nombre");
                long endMergeSort = System.nanoTime();
                logTime("MergeSort", size, startMergeSort, endMergeSort);

                long startShellSort = System.nanoTime();
                service.shellSort(1, "nombre");
                long endShellSort = System.nanoTime();
                logTime("ShellSort", size, startShellSort, endShellSort);

                long startBinarySearch = System.nanoTime();
                service.buscarBinario("nombre", String.valueOf(array[array.length / 2]));
                long endBinarySearch = System.nanoTime();
                logTime("BinarySearch", size, startBinarySearch, endBinarySearch);

                long startLinearSearch = System.nanoTime();
                service.buscarLineal("nombre", String.valueOf(array[array.length / 2]));
                long endLinearSearch = System.nanoTime();
                logTime("LinearSearch", size, startLinearSearch, endLinearSearch);

                System.out.println("---- Pruebas completadas para tamaño: " + size + " ----");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error durante las pruebas: " + e.getMessage(), e);
        }
    }

    private int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100000);
        }
        return array;
    }

    private void logTime(String algorithm, int size, long startTime, long endTime) {
        double durationInSeconds = (endTime - startTime) / 1e9;
        System.out.printf("Algoritmo: %s | Tamaño: %d | Tiempo: %.9f segundos%n", algorithm, size, durationInSeconds);
    }
}
