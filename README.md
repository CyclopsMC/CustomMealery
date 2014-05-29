## Custom Mealery

A simple Minecraft mod that allows your to define and configure your own food and meals.

# Usage
A very simple mod that allows you to configure your own food and meals by just making a simple config file for them.

When first starting the mod, a "_template.json" file will be created inside the "config/custommealery" folder. This will contain an example of all the possible configuration options for food, this file can be seen below.

```
{
id: 590,
nameid: "shinyBread",
name: "Shiny Bread",

eatTime: 12,
healAmount: 2,
saturationModifier: 0.3,

isWolfsFavoriteMeat: false,
rarity: "epic",
hasEffect: true,

isAlwaysEdible: true,
maxStackSize: 16,

potionEffects: 
[
{
potionid: 1,
probability: 1.0,
duration: 3,
amplifier: 2
},

{
potionid: 2,
probability: 1.0,
duration: 5,
amplifier: 2
}
]

recipe:
[
"264,264,264",
"264,3,264",
"264,264,264"
]
}
```

Only the first three values (id, nameid, name) must be available, the others are optional.

When you define your own food, you can use any folder structure you want, as long as it resides in the "config/custommealery" folder and ends with ".json". (All files not ending with ".json" or with "_" as a prefix will be ignored.)
Icons can be added by adding a png file with the name nameid as filename, and it must reside in the same folder as your json file for that food item.

So if you for example make a meal with the nameid 'shinyBread', a possible folder structure could be:
```
config/custommealery/breads/shinyBread.json
config/custommealery/breads/shinyBread.png
config/custommealery/breads/shinyBread.png.mcmeta
```
Note: as you can see here, mcmeta files are also allowed, so animated icons are possible!

Idea by Jadedcat.
