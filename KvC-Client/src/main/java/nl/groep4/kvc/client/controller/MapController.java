package nl.groep4.kvc.client.controller;

import nl.groep4.kvc.client.view.scene.SceneMap;
import nl.groep4.kvc.common.interfaces.KolonistenVanCatan;

public class MapController implements Controller {

    private SceneMap view;
    private KolonistenVanCatan model;

    public MapController(SceneMap view, KolonistenVanCatan model) {
	this.view = view;
	this.model = model;
    }

}
