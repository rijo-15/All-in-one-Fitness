package com.example.fitnessapp;

public class Profile {

        private String name;
        private String height;
        private String weight;
        private String goals;
        private String gender;

        public Profile() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height){
            this.height = height;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight){
            this.weight = weight;
        }

        public String getGoals() {
            return goals;
        }


        public void setGoals(String goals){
            this.goals = goals;
        }



}
