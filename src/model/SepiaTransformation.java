package model;

public class SepiaTransformation extends AColorTransformationAdjustor {

  @Override
  protected Double[][] makeColorScalars() {
    return new Double[][]{{0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}};
  }
}
