

package com.example.roombooking.ui.adduser;

import com.example.roombooking.data.network.model.Rooms;
import com.example.roombooking.ui.base.IView;

import java.util.List;
import java.util.jar.Attributes;

/**
 *  Creaated by Vinod on   19/11/17.
 */

public interface AddUserIView extends IView {

    void showSuccessDialog();

    void refreshUserData(List<Atendee> atendees);

    void resetFields();
}
