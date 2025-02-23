import java.util.*;

class SinhVien {
	private String maSV;
    private String firstName;
    private String lastName;
    private String birthdate;
    private String address;
    private String className;
    private double diemOOP;
    private double diemQuanLyDA;
    private double diemHocMay;
    private double diemCSDL;
    private double diemLapTrinhUD;

    public SinhVien(String maSV, String firstName, String lastName, String birthdate, String address, String className,
                   double diemOOP, double diemQuanLyDA, double diemHocMay, double diemCSDL, double diemLapTrinhUD) {
        this.maSV = maSV;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.address = address;
        this.className = className;
        this.diemOOP = diemOOP;
        this.diemQuanLyDA = diemQuanLyDA;
        this.diemHocMay = diemHocMay;
        this.diemCSDL = diemCSDL;
        this.diemLapTrinhUD = diemLapTrinhUD;
    }

    public double tinhDiemTrungBinh() {
        return (diemOOP + diemQuanLyDA + diemHocMay + diemCSDL + diemLapTrinhUD) / 5;
    }

    public String xepLoaiRank() {
        double dtb = tinhDiemTrungBinh();
        if (dtb >= 8.5) return "A";
        else if (dtb >= 7.0) return "B";
        else if (dtb >= 5.5) return "C";
        else if (dtb >= 4.0) return "D";
        else return "<D";
    }

    // Getter methods
    public String getClassName() { return className; }
    public String getFullName() { return lastName + " " + firstName; }
    public String getBirthdate() { return birthdate; }
    public String getAddress() { return address; }
    public String getMaSV() { return maSV; }
}

class LopHoc {
    private String maLop;
    private List<SinhVien> danhSachSV = new ArrayList<>();

    public LopHoc(String maLop) {
        this.maLop = maLop;
    }

    public void themSinhVien(SinhVien sv) {
        danhSachSV.add(sv);
    }

    public boolean xoaSinhVien(String maSV) {
        for (SinhVien sv : danhSachSV) {
            if (sv.getMaSV().equalsIgnoreCase(maSV)) {
				danhSachSV.remove(sv);
				return true;
			}
        }
        return false;
    }

    public Map<String, Integer> thongKeRank() {
        Map<String, Integer> stats = new HashMap<>();
        stats.put("A", 0);
        stats.put("B", 0);
        stats.put("C", 0);
        stats.put("D", 0);
        stats.put("<D", 0);

        for (SinhVien sv : danhSachSV) {
            String rank = sv.xepLoaiRank();
            stats.put(rank, stats.get(rank) + 1);
        }
        return stats;
    }

    public void hienThiDanhSachSV() {
        System.out.println("\nList student of class " + maLop + ":");
        System.out.printf("%-25s %-12s %-15s %-20s\n", "Name", "DOB", "Address", "Class");
        for (SinhVien sv : danhSachSV) {
            System.out.printf("%-25s %-12s %-15s %-20s\n", 
                sv.getFullName(), sv.getBirthdate(), sv.getAddress(), sv.getClassName());
        }
    }

    // Getter
    public String getMaLop() { return maLop; }
}

public class QuanLyLopHoc {
    private static List<LopHoc> danhSachLop = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while(true) {
            System.out.println("\n==== CNTT CLASS MANAGEMENT ====");
	        System.out.println("1. Show class list");
	        System.out.println("2. Detail class");
	        System.out.println("3. Add new class");
	        System.out.println("4. Add new student");
	        System.out.println("5. Delete student");
	        System.out.println("6. Exit");
	        System.out.print("Choose method: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch(choice) {
                case 1:
	                hienThiDanhSachLop();
	                break;
	            case 2:
	                xemChiTietLop();
	                break;
	            case 3:
	                themLopMoi();
	                break;
	            case 4:
	                themSinhVien();
	                break;
	            case 5:
					xoaSinhVien();
	                break;
                case 6:
					System.exit(0);
	            default:
	                System.out.println("Invalid choice!");
            }
        }
    }

    private static void hienThiDanhSachLop() {
        System.out.println("\nClass list:");
        System.out.printf("%-10s %-15s\n", "Class code", "Student ID");
        for (LopHoc lop : danhSachLop) {
            System.out.printf("%-10s %-15d\n",
                lop.getMaLop(), lop.thongKeRank().values().stream().mapToInt(Integer::intValue).sum());
        }
    }

    private static void xemChiTietLop() {
        System.out.print("Input class code: ");
        String maLop = scanner.nextLine();

        for (LopHoc lop : danhSachLop) {
            if (lop.getMaLop().equalsIgnoreCase(maLop)) {
                lop.hienThiDanhSachSV();

                Map<String, Integer> stats = lop.thongKeRank();
                System.out.println("\nStats:");
                System.out.println("A: " + stats.get("A"));
                System.out.println("B: " + stats.get("B"));
                System.out.println("C: " + stats.get("C"));
                System.out.println("D: " + stats.get("D"));
                System.out.println("<D: " + stats.get("<D"));
                return;
            }
        }
        System.out.println("Class not found!");
    }

    private static void themLopMoi() {
	    System.out.print("Input new class code: ");
	    String maLop = scanner.nextLine();
	    danhSachLop.add(new LopHoc(maLop));
	    System.out.println("Class added succesfully!");
	}

	private static void themSinhVien() {
        System.out.println("\nInput student info:");
        System.out.print("Student code: ");
        String maSV = scanner.nextLine();
        System.out.print("First name: ");
        String lastName = scanner.nextLine();
        System.out.print("name: ");
        String firstName = scanner.nextLine();
        System.out.print("DOB (dd/mm/yyyy): ");
        String birthdate = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();
        System.out.print("Class: ");
        String className = scanner.nextLine();

        System.out.println("Input score for each subject (0-10):");
        double diemOOP = nhapDiem("OOP: ");
        double diemQuanLyDA = nhapDiem("Quản lý dự án: ");
        double diemHocMay = nhapDiem("Học máy: ");
        double diemCSDL = nhapDiem("Cơ sở dữ liệu: ");
        double diemLapTrinhUD = nhapDiem("Lập trình ứng dụng: ");

        SinhVien sv = new SinhVien(maSV, firstName, lastName, birthdate, address, className,
                                  diemOOP, diemQuanLyDA, diemHocMay, diemCSDL, diemLapTrinhUD);

        for (LopHoc lop : danhSachLop) {
            if (lop.getMaLop().equalsIgnoreCase(className)) {
                lop.themSinhVien(sv);
                System.out.println("Student added succesfully!");
                return;
            }
        }
        System.out.println("Class not found " + className + "!");
    }

    private static void xoaSinhVien() {
	    System.out.print("Input class: ");
	    String maLop = scanner.nextLine();
	    System.out.print("Input student code to delete: ");
	    String maSV = scanner.nextLine();

	    for (LopHoc lop : danhSachLop) {
	        if (lop.getMaLop().equalsIgnoreCase(maLop)) {
	            boolean result = lop.xoaSinhVien(maSV);
	            if (result) {
	                System.out.println("Delete student succesfully!");
	            } else {
	                System.out.println("Student not found!");
	            }
	            return;
	        }
	    }
	    System.out.println("Class not found!");
	}

    private static double nhapDiem(String message) {
        while (true) {
            try {
                System.out.print(message);
                double diem = Double.parseDouble(scanner.nextLine());
                if (diem < 0 || diem > 10) {
                    System.out.println("Score must be from 1 to 10!");
                    continue;
                }
                return diem;
            } catch (NumberFormatException e) {
                System.out.println("Please input number!");
            }
        }
    }
}
