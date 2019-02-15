package com.xiaoxiao.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 文件名: Book
 * 描述：
 * 修改人: caixiaoxiao
 * 日期: 2019/2/13
 */
public class Book implements Parcelable{
    private String name;
    private int price;
    private int serialNumber;

    protected Book(Parcel in) {
        name = in.readString();
        price = in.readInt();
        serialNumber = in.readInt();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(price);
        dest.writeInt(serialNumber);
    }

}
