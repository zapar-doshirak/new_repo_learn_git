package com.example.myfamily;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.example.myfamily.data.MyFamilyContract.MemberEntry;

import com.example.myfamily.data.MyFamilyContract;

public class MemberCursorAdapter extends CursorAdapter {

    public MemberCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_member, viewGroup, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView lastName = (TextView) view.findViewById(R.id.lastName);
        TextView firstName = (TextView) view.findViewById(R.id.firstName);
        TextView employment = (TextView) view.findViewById(R.id.employment);
        // Extract properties from cursor
        String sLastName = cursor.getString(cursor.getColumnIndexOrThrow(MemberEntry.KEY_LAST_NAME));
        String sFirstName = cursor.getString(cursor.getColumnIndexOrThrow(MemberEntry.KEY_FIRST_NAME));
        String sEmployment = cursor.getString(cursor.getColumnIndexOrThrow(MemberEntry.KEY_EMPLOYMENT));

        lastName.setText(sLastName);
        firstName.setText(sFirstName);
        employment.setText(sEmployment);

    }

}
