/*
* Copyright (C) 2014 Vincent Mi
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.hoverdroids.roundedimageview;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class PicassoFragment extends Fragment {

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_rounded, container, false);

    PicassoAdapter adapter = new PicassoAdapter(getActivity());
    ((ListView) view.findViewById(R.id.main_list)).setAdapter(adapter);

    adapter.add(new PicassoItem("https://lh6.googleusercontent.com/-55osAWw3x0Q/URquUtcFr5I/AAAAAAAAAbs/rWlj1RUKrYI/s1024/A%252520Photographer.jpg", ScaleType.CENTER));
    adapter.add(new PicassoItem("https://lh4.googleusercontent.com/--dq8niRp7W4/URquVgmXvgI/AAAAAAAAAbs/-gnuLQfNnBA/s1024/A%252520Song%252520of%252520Ice%252520and%252520Fire.jpg", ScaleType.CENTER_CROP));
    adapter.add(new PicassoItem("https://lh5.googleusercontent.com/-7qZeDtRKFKc/URquWZT1gOI/AAAAAAAAAbs/hqWgteyNXsg/s1024/Another%252520Rockaway%252520Sunset.jpg", ScaleType.CENTER_INSIDE));
    adapter.add(new PicassoItem("https://lh3.googleusercontent.com/--L0Km39l5J8/URquXHGcdNI/AAAAAAAAAbs/3ZrSJNrSomQ/s1024/Antelope%252520Butte.jpg", ScaleType.FIT_CENTER));
    adapter.add(new PicassoItem("https://lh6.googleusercontent.com/-8HO-4vIFnlw/URquZnsFgtI/AAAAAAAAAbs/WT8jViTF7vw/s1024/Antelope%252520Hallway.jpg", ScaleType.FIT_END));
    adapter.add(new PicassoItem("https://lh4.googleusercontent.com/-WIuWgVcU3Qw/URqubRVcj4I/AAAAAAAAAbs/YvbwgGjwdIQ/s1024/Antelope%252520Walls.jpg", ScaleType.FIT_START));
    adapter.add(new PicassoItem("https://lh6.googleusercontent.com/-UBmLbPELvoQ/URqucCdv0kI/AAAAAAAAAbs/IdNhr2VQoQs/s1024/Apre%2525CC%252580s%252520la%252520Pluie.jpg", ScaleType.FIT_XY));

    return view;
  }

  static class PicassoItem {
    final String mUrl;

    final ScaleType mScaleType;
    PicassoItem(String url, ScaleType scaleType) {
      this.mUrl = url;
      mScaleType = scaleType;
    }

  }

  class PicassoAdapter extends ArrayAdapter<PicassoItem> {
    private final LayoutInflater mInflater;
    private final Transformation mTransformation;

    public PicassoAdapter(Context context) {
      super(context, 0);
      mInflater = LayoutInflater.from(getContext());
      mTransformation = new RoundedTransformationBuilder()
          .cornerRadiusDp(30)
          .borderColor(Color.BLACK)
          .borderWidthDp(3)
          .oval(false)
          .build();
    }

    @Override public View getView(int position, View convertView, @NonNull ViewGroup parent) {
      ViewGroup view;
      if (convertView == null) {
        view = (ViewGroup) mInflater.inflate(R.layout.picasso_item, parent, false);
      } else {
        view = (ViewGroup) convertView;
      }

      PicassoItem item = getItem(position);

      ImageView imageView = ((ImageView) view.findViewById(R.id.imageView1));
      imageView.setScaleType(item.mScaleType);


      Picasso.get().load(item.mUrl).fit().centerCrop().into(imageView);

      ((TextView) view.findViewById(R.id.textView3)).setText(item.mScaleType.toString());
      return view;
    }
  }
}
