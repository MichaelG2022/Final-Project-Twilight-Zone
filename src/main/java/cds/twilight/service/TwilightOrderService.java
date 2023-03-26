package cds.twilight.service;

import javax.validation.Valid;
import cds.twilight.entity.Order;
import cds.twilight.entity.OrderRequest;

public interface TwilightOrderService {

  Order createLightOrder(@Valid OrderRequest orderRequest);

} // end INTERFACE
