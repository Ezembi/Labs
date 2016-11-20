package labs.mobile.victor.lab_1.Lab_4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import labs.mobile.victor.lab_1.R;

public class Lab4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab4);
        setTitle("Lab_4");

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new LazyAdapter(this, Arrays.asList(dummyData())));

    }

    private String[] dummyData() {
        return new String[] {
                "https://raw.githubusercontent.com/google/material-design-icons/master/action/drawable-mdpi/ic_3d_rotation_black_48dp.png",
                "https://raw.githubusercontent.com/google/material-design-icons/master/action/drawable-mdpi/ic_accessibility_black_48dp.png",
                "https://raw.githubusercontent.com/google/material-design-icons/master/action/drawable-mdpi/ic_accessible_black_48dp.png",
                "https://raw.githubusercontent.com/google/material-design-icons/master/action/drawable-mdpi/ic_account_balance_black_48dp.png",
                "https://raw.githubusercontent.com/google/material-design-icons/master/action/drawable-mdpi/ic_account_box_black_48dp.png",
                "https://raw.githubusercontent.com/google/material-design-icons/master/action/drawable-mdpi/ic_account_circle_black_48dp.png",
                "https://raw.githubusercontent.com/google/material-design-icons/master/action/drawable-mdpi/ic_alarm_add_black_48dp.png",
                "https://raw.githubusercontent.com/google/material-design-icons/master/action/drawable-mdpi/ic_alarm_off_black_48dp.png",
                "https://raw.githubusercontent.com/google/material-design-icons/master/action/drawable-mdpi/ic_alarm_on_black_48dp.png",
                "https://raw.githubusercontent.com/google/material-design-icons/master/action/drawable-mdpi/ic_all_out_black_48dp.png",
                "https://raw.githubusercontent.com/google/material-design-icons/master/action/drawable-mdpi/ic_android_black_48dp.png",
                "https://raw.githubusercontent.com/google/material-design-icons/master/action/drawable-mdpi/ic_aspect_ratio_black_48dp.png",
                "https://raw.githubusercontent.com/google/material-design-icons/master/action/drawable-mdpi/ic_assessment_black_48dp.png",
                "https://raw.githubusercontent.com/google/material-design-icons/master/action/drawable-mdpi/ic_assignment_ind_black_48dp.png",
                "https://raw.githubusercontent.com/google/material-design-icons/master/action/drawable-mdpi/ic_autorenew_black_48dp.png",
                "https://raw.githubusercontent.com/google/material-design-icons/master/action/drawable-mdpi/ic_book_black_48dp.png",
                "https://raw.githubusercontent.com/google/material-design-icons/master/action/drawable-mdpi/ic_bookmark_border_black_48dp.png",
                "https://raw.githubusercontent.com/google/material-design-icons/master/action/drawable-mdpi/ic_bug_report_black_48dp.png",
                "https://raw.githubusercontent.com/google/material-design-icons/master/action/drawable-mdpi/ic_cached_black_48dp.png",
                "https://raw.githubusercontent.com/google/material-design-icons/master/action/drawable-mdpi/ic_camera_enhance_black_48dp.png",
                "https://raw.githubusercontent.com/google/material-design-icons/master/action/drawable-mdpi/ic_card_membership_black_48dp.png"




        };
    }
}
