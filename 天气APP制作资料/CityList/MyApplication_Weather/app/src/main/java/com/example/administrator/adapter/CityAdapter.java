package com.example.administrator.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;


import com.example.administrator.myapplication_weather.R;
import com.example.administrator.widget.ContactItemInterface;
import com.example.administrator.widget.ContactListAdapter;

import java.util.List;

public class CityAdapter extends ContactListAdapter
{

	public CityAdapter(Context _context, int _resource,
                       List<ContactItemInterface> _items)
	{
		super(_context, _resource, _items);
	}

	public void populateDataForRow(View parentView, ContactItemInterface item,
                                   int position)
	{
		View infoView = parentView.findViewById(R.id.infoRowContainer);
		TextView nicknameView = (TextView) infoView
				.findViewById(R.id.cityName);

		nicknameView.setText(item.getDisplayInfo());
	}

}
