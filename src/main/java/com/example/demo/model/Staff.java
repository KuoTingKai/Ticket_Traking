package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("staffs")
@Data
public class Staff {
        public enum Type {
            QA,
            RD
        }

        @Id
        private String id;
        private Type type;

        public Staff () {
            this.id = UUID.randomUUID().toString().replaceAll("-", "");
            this.type = Type.QA;
        }
        public Staff (String id, Type type) {
            this.id = id;
            this.type = type;
        }

//        public String getId() {
//            return id;
//        }
//
//        public void setId(String id) {
//            this.id = id;
//        }
//
//        public Type getType() {
//            return type;
//        }
//
//        public void setType(Type type) {
//            this.type = type;
//        }

        public String toString() {return String.format("Staff[%s] %s ", this.type, this.id);}
}
