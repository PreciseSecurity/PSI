vizmap = [ {
  "title" : "hallmarksOfCancer",
  "style" : [ {
    "selector" : "node",
    "css" : {
      "text-opacity" : 1.0,
      "width" : 70.0,
      "border-opacity" : 1.0,
      "background-color" : "rgb(255,255,255)",
      "background-opacity" : 1.0,
      "font-size" : 20,
      "shape" : "ellipse",
      "border-width" : 1.0,
      "border-color" : "rgb(0,0,0)",
      "color" : "rgb(0,0,0)",
      "height" : 30.0,
      "text-valign" : "center",
      "text-halign" : "center",
      "font-family" : "SansSerif",
      "font-weight" : "bold",
      "content" : "data(label)"
    }
  }, {
    "selector" : "node[score > 4.6]",
    "css" : {
      "width" : 140.0
    }
  }, {
    "selector" : "node[score = 4.6]",
    "css" : {
      "width" : 140.0
    }
  }, {
    "selector" : "node[score > 0.0][score < 4.6]",
    "css" : {
      "width" : "mapData(score,0.0,4.6,70.0,140.0)"
    }
  }, {
    "selector" : "node[score > -2.6][score < 0.0]",
    "css" : {
      "width" : "mapData(score,-2.6,0.0,140.0,70.0)"
    }
  }, {
    "selector" : "node[score = -2.6]",
    "css" : {
      "width" : 140.0
    }
  }, {
    "selector" : "node[score < -2.6]",
    "css" : {
      "width" : 140.0
    }
  }, {
    "selector" : "node[score > 4.6]",
    "css" : {
      "height" : 140.0
    }
  }, {
    "selector" : "node[score = 4.6]",
    "css" : {
      "height" : 140.0
    }
  }, {
    "selector" : "node[score > 0.0][score < 4.6]",
    "css" : {
      "height" : "mapData(score,0.0,4.6,70.0,140.0)"
    }
  }, {
    "selector" : "node[score > -2.6][score < 0.0]",
    "css" : {
      "height" : "mapData(score,-2.6,0.0,140.0,70.0)"
    }
  }, {
    "selector" : "node[score = -2.6]",
    "css" : {
      "height" : 140.0
    }
  }, {
    "selector" : "node[score < -2.6]",
    "css" : {
      "height" : 140.0
    }
  }, {
    "selector" : "node[copyNumber = '2']",
    "css" : {
      "border-width" : 10.0
    }
  }, {
    "selector" : "node[copyNumber = '1']",
    "css" : {
      "border-width" : 5.0
    }
  }, {
    "selector" : "node[copyNumber = '0']",
    "css" : {
      "border-width" : 1.0
    }
  }, {
    "selector" : "node[copyNumber = '-2']",
    "css" : {
      "border-width" : 10.0
    }
  }, {
    "selector" : "node[copyNumber = '-1']",
    "css" : {
      "border-width" : 5.0
    }
  }, {
    "selector" : "node[score > 4.6]",
    "css" : {
      "background-color" : "rgb(0,255,0)"
    }
  }, {
    "selector" : "node[score = 4.6]",
    "css" : {
      "background-color" : "rgb(0,255,0)"
    }
  }, {
    "selector" : "node[score > 0.0][score < 4.6]",
    "css" : {
      "background-color" : "mapData(score,0.0,4.6,rgb(255,255,255),rgb(0,255,0))"
    }
  }, {
    "selector" : "node[score > -2.6][score < 0.0]",
    "css" : {
      "background-color" : "mapData(score,-2.6,0.0,rgb(255,77,77),rgb(255,255,255))"
    }
  }, {
    "selector" : "node[score = -2.6]",
    "css" : {
      "background-color" : "rgb(255,77,77)"
    }
  }, {
    "selector" : "node[score < -2.6]",
    "css" : {
      "background-color" : "rgb(255,77,77)"
    }
  }, {
    "selector" : "node[copyNumber = '2']",
    "css" : {
      "border-color" : "rgb(0,0,255)"
    }
  }, {
    "selector" : "node[copyNumber = '1']",
    "css" : {
      "border-color" : "rgb(0,0,255)"
    }
  }, {
    "selector" : "node[copyNumber = '0']",
    "css" : {
      "border-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "node[copyNumber = '-2']",
    "css" : {
      "border-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "node[copyNumber = '-1']",
    "css" : {
      "border-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "node:selected",
    "css" : {
      "background-color" : "rgb(255,255,0)"
    }
  }, {
    "selector" : "edge",
    "css" : {
      "target-arrow-shape" : "triangle",
      "color" : "rgb(0,0,0)",
      "target-arrow-color" : "rgb(0,0,0)",
      "font-size" : 10,
      "width" : 1.0,
      "line-color" : "rgb(255,255,255)",
      "font-family" : "Dialog",
      "font-weight" : "normal",
      "content" : "",
      "text-opacity" : 1.0,
      "line-style" : "solid",
      "opacity" : 1.0,
      "source-arrow-color" : "rgb(0,0,0)",
      "source-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'expression']",
    "css" : {
      "target-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0403(colocalization)']",
    "css" : {
      "target-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'dissociation']",
    "css" : {
      "target-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'compound']",
    "css" : {
      "target-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'inhibition;phosphorylation']",
    "css" : {
      "target-arrow-shape" : "tee"
    }
  }, {
    "selector" : "edge[edgeType = 'functional']",
    "css" : {
      "target-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0407(direct interaction)']",
    "css" : {
      "target-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'inactivates']",
    "css" : {
      "target-arrow-shape" : "tee"
    }
  }, {
    "selector" : "edge[edgeType = 'Mi:0933(negative genetic interaction)']",
    "css" : {
      "target-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0799(additive genetic interaction defined by inequality)']",
    "css" : {
      "target-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'stabilizes.mrna']",
    "css" : {
      "target-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0915(physical association)']",
    "css" : {
      "target-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0217(phosphorylation reaction)']",
    "css" : {
      "target-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0935(positive genetic interaction)']",
    "css" : {
      "target-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'hydroxylates']",
    "css" : {
      "target-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0203(dephosphorylation reaction)']",
    "css" : {
      "target-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'preserves']",
    "css" : {
      "target-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0914(association)']",
    "css" : {
      "target-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'inhibition']",
    "css" : {
      "target-arrow-shape" : "tee"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0414(enzymatic reaction)']",
    "css" : {
      "target-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = '-']",
    "css" : {
      "target-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'proteolyzes']",
    "css" : {
      "target-arrow-shape" : "tee"
    }
  }, {
    "selector" : "edge[edgeType = 'hydroxylated']",
    "css" : {
      "target-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'unspecified']",
    "css" : {
      "target-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'stabilzes.mrna']",
    "css" : {
      "target-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'detectedBy']",
    "css" : {
      "target-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0794(synthetic genetic interaction defined by inequality)']",
    "css" : {
      "target-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0796(suppressive genetic interaction defined by inequality)']",
    "css" : {
      "target-arrow-shape" : "tee"
    }
  }, {
    "selector" : "edge[edgeType = 'TF cofactor']",
    "css" : {
      "target-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0194(cleavage)']",
    "css" : {
      "target-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'TF binding']",
    "css" : {
      "target-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0220(ubiquitination reaction)']",
    "css" : {
      "target-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'binding/association']",
    "css" : {
      "target-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'ubiquination']",
    "css" : {
      "target-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'activation;phosphorylation']",
    "css" : {
      "target-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'repression']",
    "css" : {
      "target-arrow-shape" : "tee"
    }
  }, {
    "selector" : "edge[edgeType = 'indirect effect']",
    "css" : {
      "target-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'activation']",
    "css" : {
      "target-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0567(neddylation reaction)']",
    "css" : {
      "target-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0191(aggregation)']",
    "css" : {
      "target-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'activates']",
    "css" : {
      "target-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'dimerize']",
    "css" : {
      "target-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'missing interaction']",
    "css" : {
      "target-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0192(acetylation reaction)']",
    "css" : {
      "target-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0217(phosphorylation)']",
    "css" : {
      "target-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0190']",
    "css" : {
      "target-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0570(protein cleavage)']",
    "css" : {
      "target-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0194(cleavage reaction)']",
    "css" : {
      "target-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'ubiquitination']",
    "css" : {
      "target-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'inhibits']",
    "css" : {
      "target-arrow-shape" : "tee"
    }
  }, {
    "selector" : "edge[edgeType = 'dephosphorylation']",
    "css" : {
      "target-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'phosphorylation']",
    "css" : {
      "target-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'expression']",
    "css" : {
      "target-arrow-color" : "rgb(0,0,170)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0403(colocalization)']",
    "css" : {
      "target-arrow-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'compound']",
    "css" : {
      "target-arrow-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'dissociation']",
    "css" : {
      "target-arrow-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'functional']",
    "css" : {
      "target-arrow-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'inhibition;phosphorylation']",
    "css" : {
      "target-arrow-color" : "rgb(221,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0407(direct interaction)']",
    "css" : {
      "target-arrow-color" : "rgb(0,170,170)"
    }
  }, {
    "selector" : "edge[edgeType = 'inactivates']",
    "css" : {
      "target-arrow-color" : "rgb(221,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'Mi:0933(negative genetic interaction)']",
    "css" : {
      "target-arrow-color" : "rgb(0,0,170)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0799(additive genetic interaction defined by inequality)']",
    "css" : {
      "target-arrow-color" : "rgb(170,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'stabilizes.mrna']",
    "css" : {
      "target-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0915(physical association)']",
    "css" : {
      "target-arrow-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0217(phosphorylation reaction)']",
    "css" : {
      "target-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'hydroxylates']",
    "css" : {
      "target-arrow-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0935(positive genetic interaction)']",
    "css" : {
      "target-arrow-color" : "rgb(0,221,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0203(dephosphorylation reaction)']",
    "css" : {
      "target-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'preserves']",
    "css" : {
      "target-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0914(association)']",
    "css" : {
      "target-arrow-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'inhibition']",
    "css" : {
      "target-arrow-color" : "rgb(221,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0414(enzymatic reaction)']",
    "css" : {
      "target-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'proteolyzes']",
    "css" : {
      "target-arrow-color" : "rgb(170,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = '-']",
    "css" : {
      "target-arrow-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'hydroxylated']",
    "css" : {
      "target-arrow-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'unspecified']",
    "css" : {
      "target-arrow-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'stabilzes.mrna']",
    "css" : {
      "target-arrow-color" : "rgb(0,0,136)"
    }
  }, {
    "selector" : "edge[edgeType = 'detectedBy']",
    "css" : {
      "target-arrow-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0794(synthetic genetic interaction defined by inequality)']",
    "css" : {
      "target-arrow-color" : "rgb(170,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0796(suppressive genetic interaction defined by inequality)']",
    "css" : {
      "target-arrow-color" : "rgb(170,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'TF cofactor']",
    "css" : {
      "target-arrow-color" : "rgb(0,0,170)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0194(cleavage)']",
    "css" : {
      "target-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'TF binding']",
    "css" : {
      "target-arrow-color" : "rgb(0,0,170)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0220(ubiquitination reaction)']",
    "css" : {
      "target-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'ubiquination']",
    "css" : {
      "target-arrow-color" : "rgb(221,0,221)"
    }
  }, {
    "selector" : "edge[edgeType = 'binding/association']",
    "css" : {
      "target-arrow-color" : "rgb(0,170,170)"
    }
  }, {
    "selector" : "edge[edgeType = 'activation;phosphorylation']",
    "css" : {
      "target-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'indirect effect']",
    "css" : {
      "target-arrow-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'repression']",
    "css" : {
      "target-arrow-color" : "rgb(221,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'activation']",
    "css" : {
      "target-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0567(neddylation reaction)']",
    "css" : {
      "target-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0191(aggregation)']",
    "css" : {
      "target-arrow-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'activates']",
    "css" : {
      "target-arrow-color" : "rgb(0,136,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'dimerize']",
    "css" : {
      "target-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'missing interaction']",
    "css" : {
      "target-arrow-color" : "rgb(170,0,170)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0192(acetylation reaction)']",
    "css" : {
      "target-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0217(phosphorylation)']",
    "css" : {
      "target-arrow-color" : "rgb(221,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0190']",
    "css" : {
      "target-arrow-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0570(protein cleavage)']",
    "css" : {
      "target-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'ubiquitination']",
    "css" : {
      "target-arrow-color" : "rgb(221,0,221)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0194(cleavage reaction)']",
    "css" : {
      "target-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'inhibits']",
    "css" : {
      "target-arrow-color" : "rgb(221,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'dephosphorylation']",
    "css" : {
      "target-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'phosphorylation']",
    "css" : {
      "target-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'expression']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0403(colocalization)']",
    "css" : {
      "width" : 4.0
    }
  }, {
    "selector" : "edge[edgeType = 'dissociation']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'compound']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'functional']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'inhibition;phosphorylation']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0407(direct interaction)']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'inactivates']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'Mi:0933(negative genetic interaction)']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0799(additive genetic interaction defined by inequality)']",
    "css" : {
      "width" : 5.0
    }
  }, {
    "selector" : "edge[edgeType = 'stabilizes.mrna']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0915(physical association)']",
    "css" : {
      "width" : 2.0
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0217(phosphorylation reaction)']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'hydroxylates']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0935(positive genetic interaction)']",
    "css" : {
      "width" : 5.0
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0203(dephosphorylation reaction)']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'preserves']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0914(association)']",
    "css" : {
      "width" : 2.0
    }
  }, {
    "selector" : "edge[edgeType = 'inhibition']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0414(enzymatic reaction)']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = '-']",
    "css" : {
      "width" : 1.0
    }
  }, {
    "selector" : "edge[edgeType = 'proteolyzes']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'hydroxylated']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'unspecified']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'stabilzes.mrna']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'detectedBy']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0794(synthetic genetic interaction defined by inequality)']",
    "css" : {
      "width" : 5.0
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0796(suppressive genetic interaction defined by inequality)']",
    "css" : {
      "width" : 5.0
    }
  }, {
    "selector" : "edge[edgeType = 'TF cofactor']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0194(cleavage)']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'TF binding']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0220(ubiquitination reaction)']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'ubiquination']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'binding/association']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'activation;phosphorylation']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'repression']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'indirect effect']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'activation']",
    "css" : {
      "width" : 6.0
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0567(neddylation reaction)']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0191(aggregation)']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'activates']",
    "css" : {
      "width" : 6.0
    }
  }, {
    "selector" : "edge[edgeType = 'dimerize']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'missing interaction']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0192(acetylation reaction)']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0190']",
    "css" : {
      "width" : 1.0
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0217(phosphorylation)']",
    "css" : {
      "width" : 7.0
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0570(protein cleavage)']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0194(cleavage reaction)']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'ubiquitination']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'inhibits']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'dephosphorylation']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'phosphorylation']",
    "css" : {
      "width" : 3.0
    }
  }, {
    "selector" : "edge[edgeType = 'expression']",
    "css" : {
      "line-color" : "rgb(0,0,170)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0403(colocalization)']",
    "css" : {
      "line-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'compound']",
    "css" : {
      "line-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'dissociation']",
    "css" : {
      "line-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'functional']",
    "css" : {
      "line-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'inhibition;phosphorylation']",
    "css" : {
      "line-color" : "rgb(221,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0407(direct interaction)']",
    "css" : {
      "line-color" : "rgb(0,170,170)"
    }
  }, {
    "selector" : "edge[edgeType = 'inactivates']",
    "css" : {
      "line-color" : "rgb(221,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'Mi:0933(negative genetic interaction)']",
    "css" : {
      "line-color" : "rgb(0,0,170)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0799(additive genetic interaction defined by inequality)']",
    "css" : {
      "line-color" : "rgb(170,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'stabilizes.mrna']",
    "css" : {
      "line-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0915(physical association)']",
    "css" : {
      "line-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0217(phosphorylation reaction)']",
    "css" : {
      "line-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'hydroxylates']",
    "css" : {
      "line-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0935(positive genetic interaction)']",
    "css" : {
      "line-color" : "rgb(0,221,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0203(dephosphorylation reaction)']",
    "css" : {
      "line-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'preserves']",
    "css" : {
      "line-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'inhibition']",
    "css" : {
      "line-color" : "rgb(221,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0914(association)']",
    "css" : {
      "line-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0414(enzymatic reaction)']",
    "css" : {
      "line-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'proteolyzes']",
    "css" : {
      "line-color" : "rgb(170,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = '-']",
    "css" : {
      "line-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'hydroxylated']",
    "css" : {
      "line-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'unspecified']",
    "css" : {
      "line-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'stabilzes.mrna']",
    "css" : {
      "line-color" : "rgb(0,0,136)"
    }
  }, {
    "selector" : "edge[edgeType = 'detectedBy']",
    "css" : {
      "line-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0794(synthetic genetic interaction defined by inequality)']",
    "css" : {
      "line-color" : "rgb(170,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0796(suppressive genetic interaction defined by inequality)']",
    "css" : {
      "line-color" : "rgb(170,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'TF cofactor']",
    "css" : {
      "line-color" : "rgb(0,0,170)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0194(cleavage)']",
    "css" : {
      "line-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'TF binding']",
    "css" : {
      "line-color" : "rgb(0,0,170)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0220(ubiquitination reaction)']",
    "css" : {
      "line-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'binding/association']",
    "css" : {
      "line-color" : "rgb(0,170,170)"
    }
  }, {
    "selector" : "edge[edgeType = 'ubiquination']",
    "css" : {
      "line-color" : "rgb(221,0,221)"
    }
  }, {
    "selector" : "edge[edgeType = 'activation;phosphorylation']",
    "css" : {
      "line-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'repression']",
    "css" : {
      "line-color" : "rgb(221,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'indirect effect']",
    "css" : {
      "line-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'activation']",
    "css" : {
      "line-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0567(neddylation reaction)']",
    "css" : {
      "line-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0191(aggregation)']",
    "css" : {
      "line-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'activates']",
    "css" : {
      "line-color" : "rgb(0,136,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'dimerize']",
    "css" : {
      "line-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'missing interaction']",
    "css" : {
      "line-color" : "rgb(170,0,170)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0192(acetylation reaction)']",
    "css" : {
      "line-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0217(phosphorylation)']",
    "css" : {
      "line-color" : "rgb(221,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0190']",
    "css" : {
      "line-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0570(protein cleavage)']",
    "css" : {
      "line-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'ubiquitination']",
    "css" : {
      "line-color" : "rgb(221,0,221)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0194(cleavage reaction)']",
    "css" : {
      "line-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'inhibits']",
    "css" : {
      "line-color" : "rgb(221,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'dephosphorylation']",
    "css" : {
      "line-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'phosphorylation']",
    "css" : {
      "line-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'expression']",
    "css" : {
      "line-style" : "solid"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0403(colocalization)']",
    "css" : {
      "line-style" : "solid"
    }
  }, {
    "selector" : "edge[edgeType = 'compound']",
    "css" : {
      "line-style" : "dotted"
    }
  }, {
    "selector" : "edge[edgeType = 'dissociation']",
    "css" : {
      "line-style" : "dotted"
    }
  }, {
    "selector" : "edge[edgeType = 'functional']",
    "css" : {
      "line-style" : "dotted"
    }
  }, {
    "selector" : "edge[edgeType = 'inhibition;phosphorylation']",
    "css" : {
      "line-style" : "dashed"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0407(direct interaction)']",
    "css" : {
      "line-style" : "solid"
    }
  }, {
    "selector" : "edge[edgeType = 'inactivates']",
    "css" : {
      "line-style" : "solid"
    }
  }, {
    "selector" : "edge[edgeType = 'Mi:0933(negative genetic interaction)']",
    "css" : {
      "line-style" : "solid"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0799(additive genetic interaction defined by inequality)']",
    "css" : {
      "line-style" : "solid"
    }
  }, {
    "selector" : "edge[edgeType = 'stabilizes.mrna']",
    "css" : {
      "line-style" : "dashed"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0915(physical association)']",
    "css" : {
      "line-style" : "dotted"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0217(phosphorylation reaction)']",
    "css" : {
      "line-style" : "dashed"
    }
  }, {
    "selector" : "edge[edgeType = 'hydroxylates']",
    "css" : {
      "line-style" : "dashed"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0935(positive genetic interaction)']",
    "css" : {
      "line-style" : "solid"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0203(dephosphorylation reaction)']",
    "css" : {
      "line-style" : "dotted"
    }
  }, {
    "selector" : "edge[edgeType = 'preserves']",
    "css" : {
      "line-style" : "solid"
    }
  }, {
    "selector" : "edge[edgeType = 'inhibition']",
    "css" : {
      "line-style" : "dotted"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0914(association)']",
    "css" : {
      "line-style" : "dotted"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0414(enzymatic reaction)']",
    "css" : {
      "line-style" : "dashed"
    }
  }, {
    "selector" : "edge[edgeType = '-']",
    "css" : {
      "line-style" : "dashed"
    }
  }, {
    "selector" : "edge[edgeType = 'proteolyzes']",
    "css" : {
      "line-style" : "solid"
    }
  }, {
    "selector" : "edge[edgeType = 'hydroxylated']",
    "css" : {
      "line-style" : "dashed"
    }
  }, {
    "selector" : "edge[edgeType = 'unspecified']",
    "css" : {
      "line-style" : "dotted"
    }
  }, {
    "selector" : "edge[edgeType = 'stabilzes.mrna']",
    "css" : {
      "line-style" : "dotted"
    }
  }, {
    "selector" : "edge[edgeType = 'detectedBy']",
    "css" : {
      "line-style" : "dashed"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0794(synthetic genetic interaction defined by inequality)']",
    "css" : {
      "line-style" : "solid"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0796(suppressive genetic interaction defined by inequality)']",
    "css" : {
      "line-style" : "solid"
    }
  }, {
    "selector" : "edge[edgeType = 'TF cofactor']",
    "css" : {
      "line-style" : "dashed"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0194(cleavage)']",
    "css" : {
      "line-style" : "solid"
    }
  }, {
    "selector" : "edge[edgeType = 'TF binding']",
    "css" : {
      "line-style" : "solid"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0220(ubiquitination reaction)']",
    "css" : {
      "line-style" : "dashed"
    }
  }, {
    "selector" : "edge[edgeType = 'ubiquination']",
    "css" : {
      "line-style" : "solid"
    }
  }, {
    "selector" : "edge[edgeType = 'binding/association']",
    "css" : {
      "line-style" : "solid"
    }
  }, {
    "selector" : "edge[edgeType = 'activation;phosphorylation']",
    "css" : {
      "line-style" : "dashed"
    }
  }, {
    "selector" : "edge[edgeType = 'repression']",
    "css" : {
      "line-style" : "solid"
    }
  }, {
    "selector" : "edge[edgeType = 'indirect effect']",
    "css" : {
      "line-style" : "dashed"
    }
  }, {
    "selector" : "edge[edgeType = 'activation']",
    "css" : {
      "line-style" : "solid"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0567(neddylation reaction)']",
    "css" : {
      "line-style" : "dashed"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0191(aggregation)']",
    "css" : {
      "line-style" : "dotted"
    }
  }, {
    "selector" : "edge[edgeType = 'activates']",
    "css" : {
      "line-style" : "solid"
    }
  }, {
    "selector" : "edge[edgeType = 'dimerize']",
    "css" : {
      "line-style" : "dotted"
    }
  }, {
    "selector" : "edge[edgeType = 'missing interaction']",
    "css" : {
      "line-style" : "dotted"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0192(acetylation reaction)']",
    "css" : {
      "line-style" : "dashed"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0217(phosphorylation)']",
    "css" : {
      "line-style" : "solid"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0190']",
    "css" : {
      "line-style" : "dashed"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0570(protein cleavage)']",
    "css" : {
      "line-style" : "solid"
    }
  }, {
    "selector" : "edge[edgeType = 'ubiquitination']",
    "css" : {
      "line-style" : "solid"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0194(cleavage reaction)']",
    "css" : {
      "line-style" : "solid"
    }
  }, {
    "selector" : "edge[edgeType = 'inhibits']",
    "css" : {
      "line-style" : "solid"
    }
  }, {
    "selector" : "edge[edgeType = 'dephosphorylation']",
    "css" : {
      "line-style" : "dotted"
    }
  }, {
    "selector" : "edge[edgeType = 'phosphorylation']",
    "css" : {
      "line-style" : "dashed"
    }
  }, {
    "selector" : "edge[edgeType = 'expression']",
    "css" : {
      "source-arrow-color" : "rgb(0,0,170)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0403(colocalization)']",
    "css" : {
      "source-arrow-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'compound']",
    "css" : {
      "source-arrow-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'dissociation']",
    "css" : {
      "source-arrow-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'functional']",
    "css" : {
      "source-arrow-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'inhibition;phosphorylation']",
    "css" : {
      "source-arrow-color" : "rgb(221,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0407(direct interaction)']",
    "css" : {
      "source-arrow-color" : "rgb(0,170,170)"
    }
  }, {
    "selector" : "edge[edgeType = 'inactivates']",
    "css" : {
      "source-arrow-color" : "rgb(221,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'Mi:0933(negative genetic interaction)']",
    "css" : {
      "source-arrow-color" : "rgb(0,0,170)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0799(additive genetic interaction defined by inequality)']",
    "css" : {
      "source-arrow-color" : "rgb(170,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'stabilizes.mrna']",
    "css" : {
      "source-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0915(physical association)']",
    "css" : {
      "source-arrow-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0217(phosphorylation reaction)']",
    "css" : {
      "source-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'hydroxylates']",
    "css" : {
      "source-arrow-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0935(positive genetic interaction)']",
    "css" : {
      "source-arrow-color" : "rgb(0,221,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0203(dephosphorylation reaction)']",
    "css" : {
      "source-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'preserves']",
    "css" : {
      "source-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0914(association)']",
    "css" : {
      "source-arrow-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'inhibition']",
    "css" : {
      "source-arrow-color" : "rgb(221,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0414(enzymatic reaction)']",
    "css" : {
      "source-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'proteolyzes']",
    "css" : {
      "source-arrow-color" : "rgb(170,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = '-']",
    "css" : {
      "source-arrow-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'hydroxylated']",
    "css" : {
      "source-arrow-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'unspecified']",
    "css" : {
      "source-arrow-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'stabilzes.mrna']",
    "css" : {
      "source-arrow-color" : "rgb(0,0,136)"
    }
  }, {
    "selector" : "edge[edgeType = 'detectedBy']",
    "css" : {
      "source-arrow-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0796(suppressive genetic interaction defined by inequality)']",
    "css" : {
      "source-arrow-color" : "rgb(170,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0794(synthetic genetic interaction defined by inequality)']",
    "css" : {
      "source-arrow-color" : "rgb(170,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'TF cofactor']",
    "css" : {
      "source-arrow-color" : "rgb(0,0,170)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0194(cleavage)']",
    "css" : {
      "source-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'TF binding']",
    "css" : {
      "source-arrow-color" : "rgb(0,0,170)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0220(ubiquitination reaction)']",
    "css" : {
      "source-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'binding/association']",
    "css" : {
      "source-arrow-color" : "rgb(0,170,170)"
    }
  }, {
    "selector" : "edge[edgeType = 'ubiquination']",
    "css" : {
      "source-arrow-color" : "rgb(221,0,221)"
    }
  }, {
    "selector" : "edge[edgeType = 'activation;phosphorylation']",
    "css" : {
      "source-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'repression']",
    "css" : {
      "source-arrow-color" : "rgb(221,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'indirect effect']",
    "css" : {
      "source-arrow-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'activation']",
    "css" : {
      "source-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0567(neddylation reaction)']",
    "css" : {
      "source-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0191(aggregation)']",
    "css" : {
      "source-arrow-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'activates']",
    "css" : {
      "source-arrow-color" : "rgb(0,136,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'dimerize']",
    "css" : {
      "source-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'missing interaction']",
    "css" : {
      "source-arrow-color" : "rgb(170,0,170)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0192(acetylation reaction)']",
    "css" : {
      "source-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0190']",
    "css" : {
      "source-arrow-color" : "rgb(0,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0217(phosphorylation)']",
    "css" : {
      "source-arrow-color" : "rgb(221,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0570(protein cleavage)']",
    "css" : {
      "source-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0194(cleavage reaction)']",
    "css" : {
      "source-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'ubiquitination']",
    "css" : {
      "source-arrow-color" : "rgb(221,0,221)"
    }
  }, {
    "selector" : "edge[edgeType = 'inhibits']",
    "css" : {
      "source-arrow-color" : "rgb(221,0,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'dephosphorylation']",
    "css" : {
      "source-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'phosphorylation']",
    "css" : {
      "source-arrow-color" : "rgb(0,170,0)"
    }
  }, {
    "selector" : "edge[edgeType = 'expression']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0403(colocalization)']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'dissociation']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'compound']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'inhibition;phosphorylation']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'functional']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0407(direct interaction)']",
    "css" : {
      "source-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'inactivates']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'Mi:0933(negative genetic interaction)']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0799(additive genetic interaction defined by inequality)']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'stabilizes.mrna']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0915(physical association)']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0217(phosphorylation reaction)']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'hydroxylates']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0935(positive genetic interaction)']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0203(dephosphorylation reaction)']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'preserves']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0914(association)']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'inhibition']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0414(enzymatic reaction)']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'proteolyzes']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = '-']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'hydroxylated']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'unspecified']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'stabilzes.mrna']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'detectedBy']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0794(synthetic genetic interaction defined by inequality)']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0796(suppressive genetic interaction defined by inequality)']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'TF cofactor']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0194(cleavage)']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'TF binding']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0220(ubiquitination reaction)']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'binding/association']",
    "css" : {
      "source-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge[edgeType = 'ubiquination']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'activation;phosphorylation']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'repression']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'indirect effect']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'activation']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0567(neddylation reaction)']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0191(aggregation)']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'activates']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'dimerize']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'missing interaction']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0192(acetylation reaction)']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0190']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0217(phosphorylation)']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0570(protein cleavage)']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'ubiquitination']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'psi-mi:MI:0194(cleavage reaction)']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'inhibits']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'dephosphorylation']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge[edgeType = 'phosphorylation']",
    "css" : {
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge:selected",
    "css" : {
      "line-color" : "rgb(255,0,0)"
    }
  } ]
}, {
  "title" : "Nested Network Style",
  "style" : [ {
    "selector" : "node",
    "css" : {
      "text-opacity" : 1.0,
      "width" : 70.0,
      "border-opacity" : 0.7450980392156863,
      "background-color" : "rgb(153,255,153)",
      "background-opacity" : 0.5882352941176471,
      "font-size" : 13,
      "shape" : "ellipse",
      "border-width" : 2.0,
      "border-color" : "rgb(0,153,0)",
      "color" : "rgb(0,0,0)",
      "height" : 30.0,
      "text-valign" : "center",
      "text-halign" : "center",
      "font-family" : "SansSerif",
      "font-weight" : "normal",
      "content" : "data(name)"
    }
  }, {
    "selector" : "node[has_nested_network = 'true']",
    "css" : {
      "shape" : "rectangle"
    }
  }, {
    "selector" : "node[has_nested_network = 'true']",
    "css" : {
      "border-width" : 5.0
    }
  }, {
    "selector" : "node[has_nested_network = 'true']",
    "css" : {
      "text-valign" : "bottom"
    }
  }, {
    "selector" : "node[has_nested_network = 'true']",
    "css" : {
      "background-color" : "rgb(255,255,255)"
    }
  }, {
    "selector" : "node[has_nested_network = 'true']",
    "css" : {
      "border-color" : "rgb(0,102,204)"
    }
  }, {
    "selector" : "node[has_nested_network = 'true']",
    "css" : {
      "color" : "rgb(0,102,204)"
    }
  }, {
    "selector" : "node[has_nested_network = 'true']",
    "css" : {
      "width" : 60.0,
      "height" : 60.0
    }
  }, {
    "selector" : "node:selected",
    "css" : {
      "background-color" : "rgb(255,255,0)"
    }
  }, {
    "selector" : "edge",
    "css" : {
      "target-arrow-shape" : "none",
      "color" : "rgb(0,0,0)",
      "target-arrow-color" : "rgb(0,0,0)",
      "font-size" : 10,
      "width" : 2.0,
      "line-color" : "rgb(51,51,51)",
      "font-family" : "Dialog",
      "font-weight" : "normal",
      "content" : "",
      "text-opacity" : 1.0,
      "line-style" : "solid",
      "opacity" : 1.0,
      "source-arrow-color" : "rgb(0,0,0)",
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge:selected",
    "css" : {
      "line-color" : "rgb(255,0,0)"
    }
  } ]
}, {
  "title" : "default",
  "style" : [ {
    "selector" : "node",
    "css" : {
      "text-opacity" : 1.0,
      "width" : 70.0,
      "border-opacity" : 1.0,
      "content" : "",
      "background-color" : "rgb(255,255,255)",
      "background-opacity" : 1.0,
      "font-size" : 20,
      "shape" : "ellipse",
      "border-width" : 1.0,
      "border-color" : "rgb(0,0,0)",
      "color" : "rgb(0,0,0)",
      "height" : 30.0,
      "text-valign" : "center",
      "text-halign" : "center",
      "font-family" : "SansSerif",
      "font-weight" : "bold"
    }
  }, {
    "selector" : "node:selected",
    "css" : {
      "background-color" : "rgb(255,255,0)"
    }
  }, {
    "selector" : "edge",
    "css" : {
      "target-arrow-shape" : "triangle",
      "color" : "rgb(0,0,0)",
      "target-arrow-color" : "rgb(0,0,0)",
      "font-size" : 10,
      "width" : 1.0,
      "line-color" : "rgb(255,255,255)",
      "font-family" : "Dialog",
      "font-weight" : "normal",
      "content" : "",
      "text-opacity" : 1.0,
      "line-style" : "solid",
      "opacity" : 1.0,
      "source-arrow-color" : "rgb(0,0,0)",
      "source-arrow-shape" : "triangle"
    }
  }, {
    "selector" : "edge:selected",
    "css" : {
      "line-color" : "rgb(255,0,0)"
    }
  } ]
}, {
  "title" : "Minimal",
  "style" : [ {
    "selector" : "node",
    "css" : {
      "text-opacity" : 1.0,
      "width" : 70.0,
      "border-opacity" : 1.0,
      "content" : "",
      "background-color" : "rgb(255,255,255)",
      "background-opacity" : 1.0,
      "font-size" : 12,
      "shape" : "rectangle",
      "border-width" : 0.0,
      "border-color" : "rgb(0,0,0)",
      "color" : "rgb(0,0,0)",
      "height" : 30.0,
      "text-valign" : "center",
      "text-halign" : "center",
      "font-family" : "Dialog",
      "font-weight" : "normal"
    }
  }, {
    "selector" : "node:selected",
    "css" : {
      "background-color" : "rgb(255,255,0)"
    }
  }, {
    "selector" : "edge",
    "css" : {
      "target-arrow-shape" : "none",
      "color" : "rgb(0,0,0)",
      "target-arrow-color" : "rgb(0,0,0)",
      "font-size" : 10,
      "width" : 4.0,
      "line-color" : "rgb(51,51,51)",
      "font-family" : "Dialog",
      "font-weight" : "normal",
      "content" : "",
      "text-opacity" : 1.0,
      "line-style" : "solid",
      "opacity" : 1.0,
      "source-arrow-color" : "rgb(0,0,0)",
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge:selected",
    "css" : {
      "line-color" : "rgb(255,0,0)"
    }
  } ]
}, {
  "title" : "Solid",
  "style" : [ {
    "selector" : "node",
    "css" : {
      "text-opacity" : 0.6274509803921569,
      "width" : 70.0,
      "border-opacity" : 1.0,
      "background-color" : "rgb(102,102,102)",
      "background-opacity" : 1.0,
      "font-size" : 18,
      "shape" : "ellipse",
      "border-width" : 0.0,
      "border-color" : "rgb(0,0,0)",
      "color" : "rgb(0,0,0)",
      "height" : 30.0,
      "text-valign" : "bottom",
      "text-halign" : "center",
      "font-family" : "Dialog",
      "font-weight" : "normal",
      "content" : "data(name)"
    }
  }, {
    "selector" : "node:selected",
    "css" : {
      "background-color" : "rgb(255,255,0)"
    }
  }, {
    "selector" : "edge",
    "css" : {
      "target-arrow-shape" : "none",
      "color" : "rgb(102,102,102)",
      "target-arrow-color" : "rgb(0,0,0)",
      "font-size" : 10,
      "width" : 12.0,
      "line-color" : "rgb(204,204,204)",
      "font-family" : "Dialog",
      "font-weight" : "normal",
      "text-opacity" : 0.7450980392156863,
      "line-style" : "solid",
      "opacity" : 1.0,
      "source-arrow-color" : "rgb(0,0,0)",
      "source-arrow-shape" : "none",
      "content" : "data(interaction)"
    }
  }, {
    "selector" : "edge:selected",
    "css" : {
      "line-color" : "rgb(255,0,0)"
    }
  } ]
}, {
  "title" : "Universe",
  "style" : [ {
    "selector" : "node",
    "css" : {
      "text-opacity" : 0.7058823529411765,
      "width" : 100.0,
      "border-opacity" : 1.0,
      "background-color" : "rgb(0,0,0)",
      "background-opacity" : 0.0,
      "font-size" : 20,
      "shape" : "ellipse",
      "border-width" : 0.0,
      "border-color" : "rgb(0,0,0)",
      "color" : "rgb(255,255,204)",
      "height" : 30.0,
      "text-valign" : "center",
      "text-halign" : "center",
      "font-family" : "Monospaced",
      "font-weight" : "bold",
      "content" : "data(name)"
    }
  }, {
    "selector" : "node:selected",
    "css" : {
      "background-color" : "rgb(255,255,0)"
    }
  }, {
    "selector" : "edge",
    "css" : {
      "target-arrow-shape" : "none",
      "color" : "rgb(0,0,0)",
      "target-arrow-color" : "rgb(0,0,0)",
      "font-size" : 10,
      "width" : 3.0,
      "line-color" : "rgb(255,255,255)",
      "font-family" : "Dialog",
      "font-weight" : "normal",
      "content" : "",
      "text-opacity" : 1.0,
      "line-style" : "dashed",
      "opacity" : 0.39215686274509803,
      "source-arrow-color" : "rgb(0,0,0)",
      "source-arrow-shape" : "none"
    }
  }, {
    "selector" : "edge:selected",
    "css" : {
      "line-color" : "rgb(255,0,0)"
    }
  } ]
}, {
  "title" : "Sample1",
  "style" : [ {
    "selector" : "node",
    "css" : {
      "text-opacity" : 1.0,
      "width" : 70.0,
      "border-opacity" : 1.0,
      "background-color" : "rgb(204,204,255)",
      "background-opacity" : 1.0,
      "font-size" : 12,
      "shape" : "ellipse",
      "border-width" : 0.0,
      "border-color" : "rgb(0,0,0)",
      "color" : "rgb(0,0,0)",
      "height" : 30.0,
      "text-valign" : "center",
      "text-halign" : "center",
      "font-family" : "Dialog",
      "font-weight" : "bold",
      "content" : "data(name)"
    }
  }, {
    "selector" : "node:selected",
    "css" : {
      "background-color" : "rgb(255,255,0)"
    }
  }, {
    "selector" : "edge",
    "css" : {
      "target-arrow-shape" : "none",
      "color" : "rgb(0,0,0)",
      "target-arrow-color" : "rgb(0,0,0)",
      "font-size" : 10,
      "width" : 1.0,
      "line-color" : "rgb(0,0,0)",
      "font-family" : "Dialog",
      "font-weight" : "normal",
      "text-opacity" : 1.0,
      "line-style" : "solid",
      "opacity" : 1.0,
      "source-arrow-color" : "rgb(0,0,0)",
      "source-arrow-shape" : "none",
      "content" : "data(interaction)"
    }
  }, {
    "selector" : "edge[interaction = 'pd']",
    "css" : {
      "line-style" : "dashed"
    }
  }, {
    "selector" : "edge[interaction = 'pp']",
    "css" : {
      "line-style" : "solid"
    }
  }, {
    "selector" : "edge[interaction = 'pd']",
    "css" : {
      "line-color" : "rgb(255,0,51)"
    }
  }, {
    "selector" : "edge[interaction = 'pp']",
    "css" : {
      "line-color" : "rgb(0,204,0)"
    }
  }, {
    "selector" : "edge:selected",
    "css" : {
      "line-color" : "rgb(255,0,0)"
    }
  } ]
} ]