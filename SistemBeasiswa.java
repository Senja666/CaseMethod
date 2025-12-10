import java.util.Scanner;

public class SistemBeasiswa {

    static final int MAX_DATA = 100;
    static int jumlahPendaftar = 0;

    static String[][] dataString = new String[MAX_DATA][3]; 
    
    static double[] dataIPK = new double[MAX_DATA];
    static double[] dataPenghasilan = new double[MAX_DATA];

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;
        
        do {
            tampilkanMenu();
            System.out.print("Pilih menu (1-5): ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    tambahData();
                    break;
                case 2:
                    tampilkanSemuaData();
                    break;
                case 3:
                    cariData();
                    break;
                case 4:
                    hitungRataRata();
                    break;
                case 5:
                    System.out.println("Keluar dari program.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
            System.out.println();
        } while (pilihan != 5);
    }

    static void tampilkanMenu() {
        System.out.println("=== Sistem Pendaftaran Beasiswa ===");
        System.out.println("1. Tambah Data Pendaftar Beasiswa");
        System.out.println("2. Tampilkan Semua Pendaftar");
        System.out.println("3. Cari Pendaftar berdasarkan Jenis Beasiswa");
        System.out.println("4. Hitung Rata-rata IPK per Jenis Beasiswa");
        System.out.println("5. Keluar");
    }

    static void tambahData() {
        if (jumlahPendaftar >= MAX_DATA) {
            System.out.println("Kapasitas penyimpanan penuh.");
            return;
        }

        System.out.print("Nama Mahasiswa: ");
        String nama = scanner.nextLine();

        System.out.print("NIM: ");
        String nim = scanner.nextLine();

        System.out.print("IPK terakhir: ");
        double ipk = scanner.nextDouble();
        scanner.nextLine();

        String jenis = "";
        boolean jenisValid = false;
        while (!jenisValid) {
            System.out.print("Jenis Beasiswa (Reguler/Unggulan/Riset): ");
            jenis = scanner.nextLine();
            
            if (jenis.equalsIgnoreCase("Reguler") || 
                jenis.equalsIgnoreCase("Unggulan") || 
                jenis.equalsIgnoreCase("Riset")) {
                jenis = jenis.substring(0, 1).toUpperCase() + jenis.substring(1).toLowerCase();
                jenisValid = true;
            } else {
                System.out.println("Input salah! Hanya boleh Reguler, Unggulan, atau Riset.");
            }
        }

        System.out.print("Penghasilan orang tua (maksimal 2000000): ");
        double penghasilan = scanner.nextDouble();
        scanner.nextLine(); 

        if (penghasilan > 2000000) {
            System.out.println("Pendaftaran dibatalkan karena penghasilan melebihi batas maksimal.");
        } else {
            dataString[jumlahPendaftar][0] = nama;
            dataString[jumlahPendaftar][1] = nim;
            dataString[jumlahPendaftar][2] = jenis;
            dataIPK[jumlahPendaftar] = ipk;
            dataPenghasilan[jumlahPendaftar] = penghasilan;

            jumlahPendaftar++;
            System.out.println("Pendaftar berhasil disimpan. Total pendaftar: " + jumlahPendaftar);
        }
    }

    static void tampilkanSemuaData() {
        if (jumlahPendaftar == 0) {
            System.out.println("Belum ada pendaftar.");
            return;
        }

        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("%-20s %-15s %-10s %-15s %-15s\n", "Nama", "NIM", "IPK", "Jenis", "Penghasilan");
        System.out.println("-------------------------------------------------------------------------");
        
        for (int i = 0; i < jumlahPendaftar; i++) {
            System.out.printf("%-20s %-15s %-10.2f %-15s %-15.0f\n", 
                dataString[i][0], 
                dataString[i][1], 
                dataIPK[i], 
                dataString[i][2], 
                dataPenghasilan[i]);
        }
        System.out.println("-------------------------------------------------------------------------");
    }

    static void cariData() {
        if (jumlahPendaftar == 0) {
            System.out.println("Belum ada data untuk dicari.");
            return;
        }

        System.out.print("Masukkan Jenis Beasiswa yang dicari: ");
        String keyword = scanner.nextLine();
        boolean ditemukan = false;

        System.out.println("Hasil Pencarian:");
        for (int i = 0; i < jumlahPendaftar; i++) {
            if (dataString[i][2].equalsIgnoreCase(keyword)) {
                System.out.println("- " + dataString[i][0] + " (NIM: " + dataString[i][1] + ") IPK: " + dataIPK[i]);
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Tidak ada pendaftar dengan jenis beasiswa tersebut.");
        }
    }
    
    static void hitungRataRata() {
        if (jumlahPendaftar == 0) {
            System.out.println("Belum ada data.");
            return;
        }

        String[] kategori = {"Reguler", "Unggulan", "Riset"};
    
        for (String kat : kategori) {
            double totalIPK = 0;
            int count = 0;

            for (int i = 0; i < jumlahPendaftar; i++) {
                if (dataString[i][2].equalsIgnoreCase(kat)) {
                    totalIPK += dataIPK[i];
                    count++;
                }
            }

            System.out.print(kat + " \t: ");
            if (count > 0) {
                double rataRata = totalIPK / count;
                System.out.printf("rata-rata IPK = %.2f\n", rataRata);
            } else {
                System.out.println("tidak ada pendaftar.");
            }
        }
    }
}