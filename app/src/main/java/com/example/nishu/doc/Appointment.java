package com.example.nishu.doc;

public class Appointment{
        public String toString()
        {
            return  regno +"  "+ name+"  "+age+"  "+dob+"  "+gen+"  "+mobno+"  "+email+"  "+add+"  "+prob+"  "+treat+"  "+appdate+"  "+token;

        }


        public Appointment(String regno, String name, String age, String dob, String gen, String mobno, String email,String add, String prob, String treat, String appdate, String token)
        {
            this.regno = regno;
            this.name = name;
            this.age = age;
            this.dob = dob;
            this.gen = gen;
            this.mobno = mobno;
            this.email = email;
            this.prob = prob;
            this.treat = treat;
            this.appdate = appdate;
            this.token = token;
            this.add=add;
        }

        String regno;
        String name;
        String age;

        public String getAdd() {
            return add;
        }

        public void setAdd(String add) {
            this.add = add;
        }

        String add;

        public String getRegno() {
            return regno;
        }

        public void setRegno(String regno) {
            this.regno = regno;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getGen() {
            return gen;
        }

        public void setGen(String gen) {
            this.gen = gen;
        }

        public String getMobno() {
            return mobno;
        }

        public void setMobno(String mobno) {
            this.mobno = mobno;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getProb() {
            return prob;
        }

        public void setProb(String prob) {
            this.prob = prob;
        }

        public String getTreat() {
            return treat;
        }

        public void setTreat(String treat) {
            this.treat = treat;
        }

        public String getAppdate() {
            return appdate;
        }

        public void setAppdate(String appdate) {
            this.appdate = appdate;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        String dob;
        String gen;
        String mobno;
        String email;
        String prob;
        String treat;
        String appdate;
        String token;
}
