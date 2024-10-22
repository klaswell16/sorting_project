import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SortingAlgorithm {
    public static class EmployeeSorter {

        private static List<Employee> readCSV(String fileName) {
            List<Employee> employees = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                br.readLine(); // Skip the header line
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    Employee emp = new Employee(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
                    employees.add(emp);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return employees;
        }


        private static void writeCSV(String fileName, List<Employee> employees) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
                pw.println("id,first_name,last_name,age");
                for (Employee emp : employees) {
                    pw.println(emp);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        private static void swapAttributes(Employee emp1, Employee emp2) {
            String tempFirstName = emp1.getFirstName();
            String tempLastName = emp1.getLastName();
            int tempAge = emp1.getAge();

            emp1.setFirstName(emp2.getFirstName());
            emp1.setLastName(emp2.getLastName());
            emp1.setAge(emp2.getAge());

            emp2.setFirstName(tempFirstName);
            emp2.setLastName(tempLastName);
            emp2.setAge(tempAge);
        }


        private static void bubbleSort(List<Employee> employees) {
            int n = employees.size();
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (employees.get(j).getAge() > employees.get(j + 1).getAge()) {
                        swapAttributes(employees.get(j), employees.get(j + 1));
                    }
                }
            }
        }


        private static void selectionSort(List<Employee> employees) {
            int n = employees.size();
            for (int i = 0; i < n - 1; i++) {
                int minIdx = i;
                for (int j = i + 1; j < n; j++) {
                    if (employees.get(j).getAge() < employees.get(minIdx).getAge()) {
                        minIdx = j;
                    }
                }
                swapAttributes(employees.get(i), employees.get(minIdx));
            }
        }


        private static void insertionSort(List<Employee> employees) {
            int n = employees.size();
            for (int i = 1; i < n; i++) {
                Employee key = new Employee(
                        employees.get(i).getEmpId(),
                        employees.get(i).getFirstName(),
                        employees.get(i).getLastName(),
                        employees.get(i).getAge()
                );
                int j = i - 1;
                while (j >= 0 && employees.get(j).getAge() > key.getAge()) {
                    swapAttributes(employees.get(j + 1), employees.get(j));
                    j = j - 1;
                }
                employees.set(j + 1, key);
            }
        }


        private static void mergeSort(List<Employee> employees) {
            if (employees.size() < 2) return;
            int mid = employees.size() / 2;
            List<Employee> left = new ArrayList<>(employees.subList(0, mid));
            List<Employee> right = new ArrayList<>(employees.subList(mid, employees.size()));

            mergeSort(left);
            mergeSort(right);
            merge(employees, left, right);
        }

        private static void merge(List<Employee> employees, List<Employee> left, List<Employee> right) {
            int i = 0, j = 0, k = 0;
            while (i < left.size() && j < right.size()) {
                if (left.get(i).getAge() <= right.get(j).getAge()) {
                    employees.set(k++, left.get(i++));
                } else {
                    employees.set(k++, right.get(j++));
                }
            }
            while (i < left.size()) {
                employees.set(k++, left.get(i++));
            }
            while (j < right.size()) {
                employees.set(k++, right.get(j++));
            }
        }


        private static void quickSort(List<Employee> employees, int low, int high) {
            if (low < high) {
                int pi = partition(employees, low, high);
                quickSort(employees, low, pi - 1);
                quickSort(employees, pi + 1, high);
            }
        }

        private static int partition(List<Employee> employees, int low, int high) {
            Employee pivot = employees.get(high);
            int i = (low - 1);
            for (int j = low; j < high; j++) {
                if (employees.get(j).getAge() < pivot.getAge()) {
                    i++;
                    swapAttributes(employees.get(i), employees.get(j));
                }
            }
            swapAttributes(employees.get(i + 1), employees.get(high));
            return i + 1;
        }


        private static void processFile(String fileName) {
            List<Employee> employees = readCSV(fileName);


            List<Employee> bubbleSorted = new ArrayList<>(employees);
            long startTime = System.nanoTime();
            bubbleSort(bubbleSorted);
            long endTime = System.nanoTime();
            System.out.println("BubbleSort on " + fileName + " took " + (endTime - startTime) / 1e6 + " ms");
            writeCSV(fileName.replace(".csv", "_bubblesort.csv"), bubbleSorted);


            List<Employee> selectionSorted = new ArrayList<>(employees);
            startTime = System.nanoTime();
            selectionSort(selectionSorted);
            endTime = System.nanoTime();
            System.out.println("SelectionSort on " + fileName + " took " + (endTime - startTime) / 1e6 + " ms");
            writeCSV(fileName.replace(".csv", "_selectionsort.csv"), selectionSorted);


            List<Employee> insertionSorted = new ArrayList<>(employees);
            startTime = System.nanoTime();
            insertionSort(insertionSorted);
            endTime = System.nanoTime();
            System.out.println("InsertionSort on " + fileName + " took " + (endTime - startTime) / 1e6 + " ms");
            writeCSV(fileName.replace(".csv", "_insertionsort.csv"), insertionSorted);


            List<Employee> mergeSorted = new ArrayList<>(employees);
            startTime = System.nanoTime();
            mergeSort(mergeSorted);
            endTime = System.nanoTime();
            System.out.println("MergeSort on " + fileName + " took " + (endTime - startTime) / 1e6 + " ms");
            writeCSV(fileName.replace(".csv", "_mergesort.csv"), mergeSorted);


            List<Employee> quickSorted = new ArrayList<>(employees);
            startTime = System.nanoTime();
            quickSort(quickSorted, 0, quickSorted.size() - 1);
            endTime = System.nanoTime();
            System.out.println("QuickSort on " + fileName + " took " + (endTime - startTime) / 1e6 + " ms");
            writeCSV(fileName.replace(".csv", "_quicksort.csv"), quickSorted);
        }

        public static void main(String[] args) {
            String[] files = {"company_a.csv", "company_b.csv", "company_c.csv", "company_e.csv"};
            for (String file : files) {
                processFile(file);
            }
        }
    }
}
