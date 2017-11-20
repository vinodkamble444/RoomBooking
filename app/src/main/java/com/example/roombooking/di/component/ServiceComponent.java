

package com.example.roombooking.di.component;

import com.example.roombooking.di.PerService;
import com.example.roombooking.di.module.ServiceModule;

import dagger.Component;

/**
 *  Creaated by Vinod on 01/02/17.
 */

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {


}
