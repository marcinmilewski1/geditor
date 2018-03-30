package com.geditor.ui.render;

import com.geditor.ui.render.model.RGBCube;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.Color3f;
import javax.vecmath.Color4f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RGBCubeView extends Frame implements ActionListener {

    protected Canvas3D myCanvas3D;
    protected Button myButton = new Button("Exit");

    protected BranchGroup buildViewBranch(Canvas3D c) {
        BranchGroup root = new BranchGroup();

        // Create a bounds for the background and behaviors
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
                100.0);

        // Set up the background
        Color3f bgColor = new Color3f(0.05f, 0.05f, 0.2f);
        Background bg = new Background(bgColor);
        bg.setApplicationBounds(bounds);
        root.addChild(bg);

        // Set up the global lights
        Color3f lColor1 = new Color3f(1.0f, 1.0f, 1.0f);
        Vector3f lDir1 = new Vector3f(-1.0f, -1.0f, -1.0f);
        Color3f alColor = new Color3f(0.2f, 0.2f, 0.2f);

        AmbientLight aLgt = new AmbientLight(alColor);
        aLgt.setInfluencingBounds(bounds);
        DirectionalLight lgt1 = new DirectionalLight(lColor1, lDir1);
        lgt1.setInfluencingBounds(bounds);
        root.addChild(aLgt);
        root.addChild(lgt1);
        Transform3D viewXfm = new Transform3D();
        viewXfm.set(new Vector3f(0.0f, 0.0f, 5.0f));
        TransformGroup viewXfmGroup = new TransformGroup(viewXfm);
        ViewPlatform myViewPlatform = new ViewPlatform();
        PhysicalBody myBody = new PhysicalBody();
        PhysicalEnvironment myEnvironment = new PhysicalEnvironment();
        viewXfmGroup.addChild(myViewPlatform);
        root.addChild(viewXfmGroup);
        View myView = new View();
        myView.addCanvas3D(c);
        myView.attachViewPlatform(myViewPlatform);
        myView.setPhysicalBody(myBody);
        myView.setPhysicalEnvironment(myEnvironment);
        return root;
    }


    protected BranchGroup buildContentBranch() {
        BranchGroup contentBranch = new BranchGroup();
        Transform3D rotateCube = new Transform3D();
        rotateCube.set(new AxisAngle4d(-0.8, 1.0, 0.0, (7 *Math.PI)/4));
//        rotateCube.set(new AxisAngle4d(-0.8, 1.0, 0.0, Math.PI + Math.PI/4));
        TransformGroup rotationGroup = new TransformGroup(rotateCube);
        contentBranch.addChild(rotationGroup);
        RGBCube cube = new RGBCube(1);

        GeometryInfo geometryInfo = new GeometryInfo(cube.getGeometryArray());
        NormalGenerator ng = new NormalGenerator();
        ng.generateNormals(geometryInfo);

        GeometryArray result = geometryInfo.getGeometryArray();

        Appearance appearance = new Appearance();
        Texture texture = new Texture2D();
        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.MODULATE);
        texture.setBoundaryModeS(Texture.WRAP);
        texture.setBoundaryModeT(Texture.WRAP);
        texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 0.0f, 0.0f));
        appearance.setTextureAttributes(texAttr);
        appearance.setTexture(texture);
        Shape3D shape = new Shape3D(result, appearance);
        rotationGroup.addChild(shape);

        return contentBranch;
    }

    public RGBCubeView() {
        myCanvas3D = createCanvas();
        VirtualUniverse myUniverse = new VirtualUniverse();
        Locale myLocale = new Locale(myUniverse);
        myLocale.addBranchGraph(buildViewBranch(myCanvas3D));
        myLocale.addBranchGraph(buildContentBranch());
        setTitle("SimpleWorld");
        setSize(400, 400);
        setLayout(new BorderLayout());
        add("Center", myCanvas3D);
        add("South", myButton);
        myButton.addActionListener(e -> dispose());
        setVisible(true);
    }

    public Canvas3D createCanvas() {
        GraphicsConfiguration config =
                SimpleUniverse.getPreferredConfiguration();
        return new Canvas3D(config);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}