package com.back_end_TN.project_tn.enums;

public enum Active {
    CHUA_HOAT_DONG(0),
    HOAT_DONG(1),
    VO_HIEU_HOA(2)
    ;
    private final int value;

    // Constructor
    Active(int value) {
        this.value = value;
    }

    // Getter
    public int getValue() {
        return value;
    }

    // Optional: Phương thức để lấy giá trị từ số nguyên
    public static Active fromValue(int value) {
        for (Active status : Active.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + value);
    }
}
