package com.safeinvest.safetrax;

import androidx.annotation.NonNull;


     public class nationspinner {
        public String label;
        public long value;
        public  String type;

        public nationspinner(String label, long value, String type) {
            this.label = label;
            this.value = value;
            this.type = type;
        }

        @NonNull
        @Override
        public String toString() {
            return label;
        }
    }

