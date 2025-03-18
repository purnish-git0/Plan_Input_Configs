Present backend code is designed for and

supports the following workflow

Front end admin is trying to set up the inputs,

input types, and constraints on, various Input objects,

based on plan Id.

Configuration is different for each different plan.

First a form is presented to enter plan information

plan name type category etc.

and is saved

Form input objects representing the input fields

store all the inputs.

For each plan id selection from 'all inputs' in the db

is presented and user selects inputs to include in the plan

once all inputs are configured with plan id,

constraints must be created.

constraints are of two types FrontEndConstraint

and ConstraintsExternalToInput

constraints can be created for individual fields

Typical workflow:-

1)Click on create plan button

Form with relevant fields opens,

User submits form with JSON with expected variables.

The backend saves the Plan in db

2) Click on create inputs

Form with relevant fields submitted and inputs 

objects are created in db

3)Associate inputs with plan

For this click or search for plan 

From a selection/like two panes one for all inputs and 

the right pane with selected inputs opens

and FE can transfer from left pane containing all inputs

in db, to right pane, the ones selected for association

with plan.

This is done and form is submitted with array

containing the selected inputs.

The db is updated by associating the inputs with the previously 

selected plan.

This is done for all plans, otherwise can 

do default selection which will include everything

4)Constraints are created

Constraints are objects which contain the constraintOn input object

and a list of constrainedBy input objects


These constraints are created. Constraint is related to input

object. It is manyToOne

Input is related to Plan, a ManyToMany relationship

Constraint objects are of two different types

There is FrontEndConstraint, for input constrained by other Input objects

We are just storing the dependencies i.e. what is dependent on what. 

The actual values of the dependencies

which in turn determine the possible values 

of the input constraintIn we do later once our db is populated.

The second kind of constraint is ConstraintsExternalToInput

This is dependent on objects called ExternalVariable.

The Front end will create these, and then use them

to define the ConstraintsExternalToInput

Once this is done, we have the db fully populated, from which point

we begin storing the actual rules

by creating new entities perhaps called Rule,

taking as input, input object and constraints

on the inputs etc.

Since in our scenario rules are not particularly complex,

hence instead of creating a dynamic rule evaluator

which computes evverything at run time,

it is better to just store everything in the db

and retrieve from db based on the rules

which are simple enough to be stored

and looked up from the db.

Or it can be a hybrod approach such as

Creating an Evaluator interface

which operates on Input object and Constraint object

Depending on type of constraint object

and type of input object, and other variables

in the context,

we have various implementations of the Evaluator interface.

We loop through the Constraint objects, selecting

for each constraint an appropriate Evaluator,

and collect the result in a form

which the Front End can use.

Strategy Design pattern, template design pattern,

or Facade Design pattern can be used.

Strategy Design patterns -->

You have a EvaluationContext, with the Input and Set of constraints,

You have EvaluationStrategy, which is dependent on the kind of

constraint and input.

EvaluationStrategy implementations are returned by Factory class

based on the constraint type and input,

evaluate(Input input, Constraint constraint)

is triggered inside the context, results

are accumulated, and this composite object

is returned to Front End;

Template design pattern-->

interfaces like Evaluator are created

which contain method like evaluate(Input input, Constraint constraint)

a workflow(template) like

looping through constraints is created.

At each stage, an appropriate evaluation is performed,

based on the input type and parameters.

Result is accumulated and returned to FE.

Facade design pattern-->

We have an EvaluationFacade

evaluate method on the Facade is called.

Dependent on input type and parameters,

processing is delegated to appropriate implementation

of the Evaluator, and results are collected in a

Collection and sent to Front End.
