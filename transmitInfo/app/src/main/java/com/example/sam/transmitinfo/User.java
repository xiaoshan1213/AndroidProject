package com.example.sam.transmitinfo;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by hasee on 2015/12/16.
 */
//public class User implements Serializable{
public class User implements Parcelable{


    private String name;
    private int age;

    public User(String name,int age){
        this.name=name;
        this.age=age;

    }
    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getName());
        dest.writeInt(getAge());
//        Bundle b=new Bundle();
//        b.putSring("data","helloworld");
//        dest.writeBundle(b);
    }

    public static final Creator<User> CREATOR=new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            //source.writeBundle(new Bundle());
            return new User(source.readString(),source.readInt());
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
