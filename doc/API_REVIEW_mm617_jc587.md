##Part 1
1. They are using entity component system design pattern for their game. We have a system where elements contain behaviors and responses to events.
2. Their front end has very limited access to all other parts to the program. For us, Game authoring has no idea what game engine is, which helps encapsulation.  
3. My part is heavily linked to the game elements, because of events, collision detection, and removing adding elements. Jeremy's part is linked to authoring, because the authoring needs to be able to work with the engine interface. Doing it through shared methods/resource files.
4. Jeremey will deal with Improper initialization. My part includes bad object. 
5. My API design is good because it offers a lot of flexibility, so that the user can create new things within the application easily. I'm also hoping to implement parallel streams, which would really help time. Jeremy's design is good because they use composition.

##Part 2
1. Jeremey is working on collision component/systems use cases. I'm working on event handling use cases, currently creating collision events. They are somewhat descriptive, but very appropriate.
2. Mine will hopefully done by the end of the week. So will his.
3. Jeremey and I are both excited to try to get animation.
4. I'm worried about colliding objects creating shaking. Jeremey is worried that his new event structure is robust for scripting complex events.
5. This weekend, I hope to finish collision events. Same as my whole group. Jeremey is also planning to do level scrolling. 