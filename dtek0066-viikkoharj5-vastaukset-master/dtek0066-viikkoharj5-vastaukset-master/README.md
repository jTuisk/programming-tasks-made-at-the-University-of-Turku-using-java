# DTEK0066 project template

## Workflow

```bash
$ git clone https://gitlab.utu.fi/tech/education/ooj/ooj-harj5
$ cd ooj-harj5

< tee tehtävät >

< editoi AUTHORS.TXT >

$ git add -A
$ git commit -m 'Tehtävät tehty'
$ git remote add oma https://gitlab.utu.fi/omatunnus/omaprojekti
$ git push oma

< korjaa/täydennä tehtäviä >

$ git add -A
$ git commit -m 'Tehtävät korjattu'
$ git push oma
```

## Using Maven

```bash
$ mvn clean
$ mvn compile
$ mvn exec:java
$ mvn test
$ mvn surefire-report:report
```

## Online report

See <https://gitlab.utu.fi/tech/education/ooj/ooj-harj5/pages>

Your version: https://USERNAME.utugit.fi/ooj-harj5/
